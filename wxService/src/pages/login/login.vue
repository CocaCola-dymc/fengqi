<template>
    <div>
      <van-cell-group>
        <!-- 登录表单 -->
        <van-field
          type="text"
          label="用户名"
          placeholder="请输入用户名"
          v-model="username"
          @blur.native.capture="checkUsername"
          :state="usernameState"
          icon="user-o"
        />

        <van-field
          type="password"
          label="密码"
          placeholder="请输入密码"
          v-model="password"
          @blur.native.capture="checkPassword"
          :state="usernameState"
          icon="eye-o"
        />
      </van-cell-group>
      
      <div class="button">
        <van-button round type="danger" icon="replay">重置</van-button>
        <van-button round type="primary" icon="passed">登陆</van-button>
      </div>

    </div>
  </template>
  
  <script>

  
  export default {
    data () {
      return {
        username:'',
        password:''
      };
    },
  
    components: {},
  
    methods: {

    },
  
    onChange(event){
      console.log(event.detail)
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
  