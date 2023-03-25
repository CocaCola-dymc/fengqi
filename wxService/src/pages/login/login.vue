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
          :state="passwordState"
          icon="eye-o"
        />
      </van-cell-group>

      <div class="button">
        <van-button round type="danger" icon="replay" @click="onReset">重置</van-button>
        <van-button round type="primary" icon="completed" @click="onLogin">登陆</van-button>
      </div>

    </div>
  </template>
  
  <script>

//初始化定义
const baseUrl = 'http://127.0.0.1:8080'
  
  export default {
    data () {
      return {
        username: '',
        password: '',
      };
    },
  
    methods: {
      onReset(){
        this.username = "";
        this.password = "";
      },

      checkUsername(){

      },
      onLogin(){
        wx.request({
          url: baseUrl+'/user/doQueryUsers',
          method: "POST",
          data:{
            username: this.username,
            password: this.password,
          },
          header: { 'content-type': 'application/x-www-form-urlencoded'},
          success: (res) => {
            let data = res.data;
            //判断data的长度,从而判断登录数据是否正确
            //如果row=1,则正确;如果row=0,则不正确
            let row = data.length;
            this.row = row;
          }
        })

        //注册一个定时器,目的是等请求得到数据后在进行判断
        setTimeout(() => {
          if(this.row == 1){                                              // 登陆成功
          wx.showToast({
            title: '登录成功!',
            icon: 'success',
            duration: 1000
          })

          //注册一个定时器,延时2000ms后跳转页面
          setTimeout(()=>
          {
            //navigateTo, redirectTo 只能打开非 tabBar 页面。
            //switchTab 只能打开 tabBar 页面。
            //reLaunch 可以打开任意页面。
            wx.reLaunch({ 
              url: '/pages/index/main?flag_user=1&flag_admin=0', //1表示true,0表示false
            });
          }, 1000)  
        }else{
          wx.showToast({
            title: '用户名密码错误!',
            icon: 'error',
            duration: 2000
          })
        }
        }, 100);
      }
    },

    onLoad(){
      var that = this;
      wx.request({
        url: baseUrl+'/user/doFindUsers',
        method: "GET",
        //成功回调函数
        success: (res) => {
          let data = res.data;
          console.log(data);
          var user = '';
          var users = [];
          for(let i=0;i<data.length;i++){
            user = data[i].username;
            console.log(user);
            users.push(user);
          }
          this.users = users;
        }
      })
    },
  }
  // components: {},
  </script>
  
  <style scoped>
  .button{
      display: flex;
      justify-content: space-around;
      font-size: 24px;
      margin-top: 60px;
    }

  </style>
  