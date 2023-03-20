<template>
    <div>
      <van-cell-group>
        <!-- 注册表单 -->
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

        <van-field
          type="text"
          label="手机号码"
          placeholder="请输入手机号码"
          v-model="phone"
          @blur.native.capture="checkPhone"
          :state="phoneState"
          icon="phone-o"
        />
      </van-cell-group>
      
      <div class="button">
        <van-button round type="danger" icon="replay">重置</van-button>
        <van-button round type="primary" icon="edit">注册</van-button>
      </div>

    </div>
  </template>
  
  <script>

  
  export default {
    data () {
      return {
        username:'',
        password:'',
        phone:''
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
  