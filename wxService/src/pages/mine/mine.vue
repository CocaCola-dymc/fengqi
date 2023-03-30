<template>
  <div>
    <div class="container">
      <div class="box_user" @click="onLogout">
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

      <van-action-sheet
        :show="show"
        :actions="actions"
        cancel-text="取消"
        description="确定要退出登陆吗"
        @close="onClose"
        @select="onSelect"
      />
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
      show: false,
      actions:[{name:'退出登录',color:'#ee0a24'}]
    }
  },

  methods:{
    settings(){
      wx.navigateTo({
        url: '/pages/settings/main',
      }); 
    },

    chargeRecord(){
      wx.navigateTo({
        url: '/pages/charge/main?active=1',   // active=1,切换到内容2
      })
    },

    onLogout(){           //点击用户栏弹出退出提示
      this.show = true;
    },

    onClose(){
      this.show = false   //点击屏幕关闭退出提示
    },

    onSelect(){
      wx.showToast({
        title: '已退出登录',
        icon: 'success',
        duration: 1000
      })
      setTimeout(() => {
        wx.navigateTo({
          url: '/pages/welcome/main'
        })
      }, 1000);
      
    }

  },
  
  onLoad(){
    this.username = this.globalData.username;
    this.flag_user  = this.globalData.flag_user;
    this.flag_admin = this.globalData.flag_admin;
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
