<template>
  <div>
    <van-tabs :active="active">
      <van-tab title="充电情况">
        <div v-if="flag_charge" class="container">
          <van-row>
            <van-col offset="4" span="8">充电套餐</van-col>
            <van-col offset="4" span="8">剩余时间</van-col>
          </van-row>
          <van-row v-for="(item,index) in chargeArray" :key="index">
            <van-col offset="3" span="8">{{ item }}</van-col>
            <van-col offset="5" span="8">
              <van-count-down :time="time" @finish="onFinished"/>
            </van-col>
          </van-row>
        </div>
        <div v-else>
          <van-row>
            <van-col offset="8" span="8">当前无正在充电</van-col> 
          </van-row>
        </div>
      </van-tab>

      <van-tab title="充电记录">
        <div class="container">
          <van-row>
            <van-col offset="0" span="5">充电用户</van-col>
            <van-col offset="3" span="5">充电套餐</van-col>
            <van-col offset="4" span="6">充电时间</van-col>
          </van-row>
          <van-row v-for="(item,index) in data" :key="index">
            <van-col offset="1" span="3">{{ item.username }}</van-col>
            <van-col offset="3" span="6">{{ item.combo }}</van-col>
            <van-col offset="3" span="7">{{ item.createdTime }}</van-col>
          </van-row>
        </div>
      </van-tab>
    </van-tabs>
  </div>
</template>

<script>

const baseUrl = 'http://127.0.0.1:8080'

export default {
  data () {
    return {
      active: 0,    //默认为标签1
      flag_charge: null,    //当前有无正在充电的标志位
      chargeArray: [],
      time: null,
      data: '',
    }
  },

  methods:{
    onFinished(){
      wx.showToast({
        title: "充电完成",
        icon: 'success',
        duration: 1000
      })
      this.flag_charge = false
    }
  },

  onLoad(option){
    let combo = option.combo;
    let time = option.time;
    let username = option.username;
    let active = option.active;   //接收mine.vue传入的参数
    let flag_charge = Boolean(Number(option.flag_charge));
    let flag_admin = Boolean(Number(option.flag_admin));
    let flag_user = Boolean(Number(option.flag_user));
    // console.log(flag_admin, flag_user)

    let chargeArray=[];
    chargeArray.push(combo);
    this.chargeArray = chargeArray;
    this.time = Number(time)*1000;
    this.flag_charge = flag_charge;
    this.active = Number(active);
    this.flag_admin = flag_admin;
    this.flag_user = flag_user;

    if(flag_user){      //如果是普通用户，就查询个人充电记录
      console.log(1)
      wx.request({
        url: baseUrl+'/fengqi/doQueryLog',
        methods: "POST",
        data:{
          username: username,
        },
        header: { 'content-type': 'application/x-www-form-urlencoded'},
        success:(res)=>{
          let data = res.data;
          this.data = data;
        }
      })
    }else if(flag_admin){   //如果是管理员，则查询所有人的记录
      console.log(2)
      wx.request({
        url: baseUrl+'/fengqi/doQueryLogs',
        methods: "POST",
        // data:{
        //   username: username,
        // },
        header: { 'content-type': 'application/x-www-form-urlencoded'},
        success:(res)=>{
          let data = res.data;
          this.data = data;
        }
      })
    }

    
  }

}
</script>

<style scoped>
</style>
