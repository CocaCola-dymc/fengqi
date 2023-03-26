<template>
    <div>
      <!-- <van-col span="10" offset="8">欢迎使用,请先登录</van-col> -->
      <div class="title">
        欢迎使用,请先登录
      </div>
      <div class="button">
        <van-button class="admin" round icon="manager-o" type="info" @click="adminLogin">管理人员登录</van-button>
        <van-button class="user" round icon="user-o" type="primary" @click="userLogin">普通用户登录</van-button>
      </div>
      <a class="register" @click="register">没有账号?前往注册--></a>
    </div>
  </template>
  
  <script>

  
  export default {
    data () {
      return {
      };
    },
  
    components: {},
  
    methods: {
      adminLogin(){
        wx.navigateTo({
          url:'/pages/login/main?flag_user=0&flag_admin=1'
        })
      },
      userLogin(){
        wx.navigateTo({
          url:'/pages/login/main?flag_user=1&flag_admin=0'
        })
      },
      register(){
        wx.navigateTo({
          url:'/pages/register/main'
        })
      }
    },
  
    onLoad(){
      
    }
  }
  </script>
  
  <style scoped>
    .title{
      display: flex;
      justify-content: center;
      font-size: 24px;
      font-weight: bold;
      margin:60px 0
    }
    .button{
      display: flex;
      flex-direction: column;
      align-items: center;
      font-size: 24px;
    }
    .button .admin{
      margin: 30px;
    }
    .button .user{
      margin-bottom: 30px;
    }
    .register{
      font-size: 16px;
      display: flex;
      justify-content: center;
      text-decoration: underline;
    }
  </style>
  