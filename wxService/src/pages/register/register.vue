<template>
    <div>
      <van-cell-group>
        <!-- 登录表单 -->
        <van-field
          type="text"
          label="用户名"
          placeholder="请输入用户名"
          :value="username"
          @input="username = $event.mp.detail"
          @blur="checkUsername"
          :state="usernameState"
          icon="user-o"
        />

        <van-field  
          type="password"
          label="密码"
          placeholder="请输入密码"
          :value="password"
          @input="password = $event.mp.detail"
          @blur="checkPassword"
          :state="usernameState"
          icon="eye-o"
        />

        <van-field  
          type="phone"
          label="手机号码"
          placeholder="请输入手机号码"
          :value="phone"
          @input="phone = $event.mp.detail"
          @blur="checkPhone"
          :state="phoneState"
          icon="phone-o"
        />
      </van-cell-group>

      <div class="button">
        <van-button round type="danger" icon="replay" @click="onReset">重置</van-button>
        <van-button round type="primary" icon="completed" @click="onRegister">注册</van-button>
      </div>

    </div>
  </template>
  
  <script>

//初始化定义
const baseUrl = 'http://127.0.0.1:8080'
  
  export default {
    data () {
      return {
        username:'',
        password:'',
        phone:'',
        checkUsername1: null,
        usernameState: '',
        passwordState: '',
        phoneState: '',
      };
    },
  
  
    methods: {
      onLoad(){
        // // 前台判断username是否存在数据库
        // wx.request({
        //   url: baseUrl+'/user/doFindUsers',
        //   method: "GET",
        //   //成功回调函数
        //   success: (res) => {
        //     let data = res.data;
        //     // console.log(data);
        //     let user = '';
        //     let users = [];
        //     for(let i=0;i<data.length;i++){
        //       user = data[i].username;
        //       users.push(user);
        //     }
        //     this.users = users;
        //   }
        // })
      },

      //重置功能函数
      onReset(){
        this.username = "";
        this.password = "";
        this.phone = "";
        wx.showToast({
          title: '重置成功',
          icon: 'success',
          duration: 500
        })
      },

      //校验用户名，防止重名或为空
      checkUsername(){
        wx.request({
          url: baseUrl+'/user/doQueryUser',
          method: "POST",
          data:{
            username: this.username,
          },
          header: { 'content-type': 'application/x-www-form-urlencoded'},
          //成功回调函数
          success: (res) => {
            let data = res.data;
            let row = data.length;
            this.row = row;
            // // 前台判断username是否存在数据库
            // let user = '';
            // let users = [];
            // for(let i=0;i<data.length;i++){
            //   user = data[i].username;
            //   users.push(user);
            // }
            // this.users = users;
          }
        })
        // if(this.users.indexOf(this.username) != -1){    //如果新建的用户名存在数据库中
        //注册一个定时器,目的是等请求得到数据后在进行判断
        setTimeout(() => {
          if(this.username == ''){                                 //this.row == 1,说明username存在于数据库
          wx.showToast({
            title: '用户名不能为空',
            icon: 'error',
            duration: 1000
          })
          this.usernameState = 'error';
          // return false;
          this.checkUsername1 = false;
        }else if(this.row == 1){                  //如果新建的用户名为空
          wx.showToast({
            title: '用户名已存在',
            icon: 'error',
            duration: 1000
          })
          this.usernameState = 'error';
          // return false;
          this.checkUsername1 = false;
        }else{                                          //用户名验证成功
          this.usernameState = 'success';
          // return true;
          this.checkUsername1 = true
        }
        }, 50);
        
      },

      //校验密码
      checkPassword(){
        let passwordRegExp = /^[0-9a-zA-Z_]{2,8}$/
        if(passwordRegExp.test(this.password)){    
          this.passwordState = 'success';
          return true;
        }else{                                          
          wx.showToast({
            title: '密码应在2~10位',
            icon: 'error',
            duration: 1000
          })
          this.passwordState = 'error';
          return false;
        }
      },

      //校验手机号
      checkPhone(){
        let phoneRegExp = /^1[3-9]\d{9}$/
        if(phoneRegExp.test(this.phone)){
          this.phoneState = 'success';
          return true;
        }else{
          wx.showToast({
            title: '手机号格式错误',
            icon: 'error',
            duration: 1000
          })
          return false;
        }
      },

      //注册按钮功能
      onRegister(){
        if(this.checkUsername1 && this.checkPassword() && this.checkPhone()){   //如果三项都正确
          wx.request({
            url: baseUrl+'/user/doInsertUser',
            method: "POST",
            data:{
              username: this.username,
              password: this.password,
              phone: this.phone,
            },
            header: { 'content-type': 'application/x-www-form-urlencoded'}, // 改变默认值为这个配置
            success: (res)=>{
              if(res.data==1){                                              // 登陆成功
                wx.showToast({
                  title: '成功!即将跳转',
                  icon: 'success',
                  duration: 2000
                })
              }
              //注册一个定时器,延时2000ms后跳转页面
              setTimeout(()=>
              {
                //navigateTo, redirectTo 只能打开非 tabBar 页面。
                //switchTab 只能打开 tabBar 页面。
                //reLaunch 可以打开任意页面。
                wx.reLaunch({ 
                url: '/pages/index/main?flag_user=1&flag_admin=0', //1表示true,0表示false
              });
              }, 2000)  
            }
          })
        }else if(!this.checkUsername1){
          wx.showToast({
            title: '用户名设置错误',
            icon: 'error',
            duration: 2000
          })
        }else if(!this.checkPassword()){
          wx.showToast({
            title: '密码应在2~10位',
            icon: 'error',
            duration: 2000
          })
        }else{
          wx.showToast({
            title: '手机号格式错误',
            icon: 'error',
            duration: 2000
          })
        }
      }
    }
  }
  

  </script>
  
  <style scoped>
  .button{
      display: flex;
      justify-content: space-around;
      font-size: 24px;
      margin-top: 60px;
    }

  </style>
  