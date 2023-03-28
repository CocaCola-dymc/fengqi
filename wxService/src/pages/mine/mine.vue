<template>
  <div>
    <div class="container">
      <div class="box_user">
        <van-image class="img" round width="60px" height="60px" src="/static/avatar/kunkun.png"></van-image>
        <div class="text">{{ username }}</div>
      </div>
      <div class="box" @click="chargeRecord">
        <van-icon class="icon" name="notes-o" size="30px"></van-icon>
        <div class="text">充电记录</div>
        <div class="arrow">>&nbsp;&nbsp;&nbsp;</div>
      </div>
      <div class="box" @click="settings">
        <van-icon class="icon" name="setting-o" size="30px"></van-icon>
        <div class="text">设置</div>
        <div class="arrow">>&nbsp;&nbsp;&nbsp;</div>
      </div>
    </div>
  </div>
</template>

<script>
// import { formatTime } from '@/utils/index'

export default {
  data () {
    return {
      username: '',
      flag_admin: null,
      flag_user: null,
    }
  },

  methods:{
    settings(){
      wx.navigateTo({
        url: '/pages/settings/main',
        success: (result) => {
        },
      }); 
    },

    chargeRecord(){
      wx.navigateTo({
        url: `/pages/charge/main?active=1&username=${this.username}&flag_admin=${this.flag_admin}&flag_user=${this.flag_user}`,   // active=1,切换到内容2
      })
    }

  },

  onLoad(option){
    let username = option.username;
    let flag_admin = option.flag_admin;   //传递给charge.vue
    let flag_user = option.flag_user;     //传递给charge.vue
    this.username = username;
    this.flag_admin = flag_admin;
    this.flag_user  = flag_user;
  }
}
</script>

<style scoped>
.box_user{
  background-color: white;
  border-bottom: 1px solid #ccc;
  height: 120px;
  display: flex;
  align-items: center;
}
.box_user .img{
  margin-left: 20px;
}
.box_user .text{
  margin-left: 10px;
  font-size: 24px;
  font-weight: bold;
}
.box{
  background-color: white;
  height: 60px;
  display: flex;
  align-items: center;
  border-top: 1px solid #ccc
}
.box .icon{
  margin-left: 10px;
}
.box .text{
  margin-left: 10px;
  font-size: 16px;
}
.box .arrow{
  margin-left: auto;
}
</style>
