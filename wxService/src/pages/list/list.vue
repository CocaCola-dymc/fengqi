<template>
  <div>
    <div class="container">
      <van-row class="row">
        <van-col offset="1" span="3">用户</van-col>
        <van-col offset="4" span="5">电话号码</van-col>
        <van-col offset="2" span="5">加入时间</van-col>
        <van-col offset="1" span="3">操作</van-col>

      </van-row>
      <van-row v-for="(item,index) in data" :key="index">
        <van-col offset="1" span="3">{{ item.username }}</van-col>
        <van-col offset="3" span="6">{{ item.phone }}</van-col>
        <van-col offset="3" span="3">{{ item.createdTime }}</van-col>
        <van-col offset="2" span="3" @click="onDelete(item,index)">删除</van-col>
      </van-row>
    </div>
    <van-button class="button" round type="primary" icon="replay" @click="onIndex">返回首页</van-button>
  </div>
</template>

<script>

const baseUrl = 'http://127.0.0.1:8080'

export default {
  data () {
    return {
      data: '',
    }
  },

  methods:{
    //删除用户
    onDelete(item,index){
      wx.showModal({
        title: '提示',
        content: `确认删除${item.username}用户吗?`,
        success(res){
          if(res.confirm){     //如果点击了确认
            wx.request({
              url: baseUrl + '/fengqi/doDeleteUser',
              method: "POST",
              data:{
                username: item.username,
              },
              header: { 'content-type': 'application/x-www-form-urlencoded'},
              success: (res) =>{
                let data = res.data;
                if(data == 1){
                  wx.showToast({
                    title: '删除成功',
                    icon: 'success',
                    duration: 1000,
                  })
                  setTimeout(() => {
                    // this.onLoad()    //刷新页面
                    wx.navigateTo({
                      url: '/pages/list/main'
                    })
                  }, 1000);
                }
              }
            })
          }else if(res.cancel){
            console.log("取消删除")
          }
        }
      })
      
    },

    onIndex(){
      wx.reLaunch({
        url: '/pages/index/main'
      })
    }
  },

  onLoad(){
    //开始发送http请求
    wx.request({
      url: baseUrl + '/fengqi/doFindUsers',
      method: "POST",
      header: { 'content-type': 'application/x-www-form-urlencoded'},
      success: (res) =>{
        let data = res.data;
        this.data = data;
      }
    }) 
  }
}
</script>

<style scoped>

.button{
  position: fixed;
  bottom: 40px;
  left: 133px;
}
</style>
