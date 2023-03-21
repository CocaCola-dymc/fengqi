<template>
  <div>
    <div class="container">
      <div class="box_user">
        <van-image class="img" round width="60px" height="60px" src="/static/avatar/kunkun.png"></van-image>
        <div class="text">****</div>
      </div>
      <div class="box">
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

    }
  },

  methods:{
    settings(){
      wx.navigateTo({
        url: '/pages/settings/main',
        success: (result) => {
        },
      });
        
    }
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
