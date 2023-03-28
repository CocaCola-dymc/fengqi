<template>
  <div>
    <div  v-if="flag_user">
      <div class="picture">
        <!-- <img src="../../../static/images/qihuo.png" alt=""> -->
        <img src="../../../static/images/anquan.jpg" alt="">
      </div>
      <div class="user">
        <van-image class="img" round width="60px" height="60px" src="/static/avatar/kunkun.png"  @click="onMine"></van-image>
        <div class="text"  @click="onMine">
          <div>用户:{{ username }}</div>
          <div>余额:{{ money }}.00</div>
        </div>
        <van-button class="deposit" icon="gold-coin-o" type="primary" round @click="onDeposit">充值</van-button>
      </div>
      <div class="choose">
        <div>请选择充电套餐</div>
      </div>
      <div class="combo" v-for="(item,index) in chooseArrays" :key="index">
        <div class="text">{{ item }}</div>
        <van-button class="start" icon="paid" type="info" round @click="onStart(item,index)">启动</van-button>
      </div>
    </div>

    <div v-if="flag_admin">
      <div class="operate">
        <div>管理员操作</div>
      </div>
      <div class="container">
        <div class="box">
          <van-image class="image" width="64px" height="64px" src="/static/images/wendu.png"></van-image>
          <div class="text">
            <div>温度</div>
            <div>24°C</div>
          </div>
        </div>
        <div class="box">
          <van-image class="image" width="64px" height="64px" src="/static/images/deng.png"></van-image>
          <div class="text">
            <div>车场灯</div>
            <van-switch v-model="checked" bind:change="onChange" />
          </div>
        </div>
        <div class="box">
          <van-image class="image" width="64px" height="64px" src="/static/images/fengmingqi.png"></van-image>
          <div class="text">
            <div>报警器</div>
            <div>关闭</div>
          </div>
        </div>
        <div class="box">
          <van-image class="image" width="40px" height="40px" src="/static/images/zhuangtai.png"></van-image>
          <div class="text">
            <div>充电详情</div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { connect } from "mqtt";

const mqttHost = "106.55.60.213"
const mqttPort = "8083";
const mqttUrl = `wx://${mqttHost}:${mqttPort}/mqtt`;
const baseUrl = 'http://127.0.0.1:8080'

const publish_Topic = "door_topic";         //发布主题
const subscribe_Topic = "card_topic";       //订阅主题

export default {
  data () {
    return {
      flag_user: null,
      flag_admin: null,
      client:{},
      info:{},
      chooseArrays: ['1￥ / 10秒钟','2￥ / 20分钟','3￥ / 40分钟','4￥ / 60分钟','5￥ / 80分钟'],
      username:'',
      money:'',
    };
  },

  components: {},

  methods: {
    onDeposit(){
      wx.navigateTo({
        url: `/pages/deposit/main?username=${this.username}`,
      })
    },

    onStart(item,index){
      this.item = item;
      wx.navigateTo({
        //传入用户名username、套餐名item、余额money
        url: `/pages/combo/main?username=${this.username}&item=${this.item}&money=${this.money}`,   
      })
    },

    onMine(){   //跳转到mine.vue
      wx.reLaunch({
        url: `/pages/mine/main?username=${this.username}&flag_admin=${this.flag_admin1}&flag_user=${this.flag_user1}`, 
      })
    }
  },

  onLoad(option){
    //获取注册页面传入的参数
    //传入的参数为string类型，需要转换成Number类型,再转换成Boolean类型
    //先将字符串"1"和"0"转换成数字1和0,再通过Boolean转换成true和false
    this.flag_user = Boolean(Number(option.flag_user));
    this.flag_admin = Boolean(Number(option.flag_admin));
    this.flag_user1 = option.flag_user;     //传递给mine.vue
    this.flag_admin1 = option.flag_admin;    //传递给mine.vue
    this.username = option.username;
    // this.username = 'tony';
    //开始发送http请求
    wx.request({
      url: baseUrl + '/fengqi/doQueryUser',
      method: "POST",
      data:{
        username: this.username,
      },
      header: { 'content-type': 'application/x-www-form-urlencoded'},
      success: (res) =>{
        let data = res.data;
        this.username = data[0].username;
        this.money = data[0].money;
      }
    })

    var that = this 
    //连接MQTT
    that.client = connect(mqttUrl)

    //连接成功时返回的信息
    that.client.on("connect",function (){
      console.log("connect ok")
      //订阅主题
      that.client.subscribe(subscribe_Topic,function(err){
        if(!err){
          console.log('成功订阅topic')
        }
      })
      //接收topic的信息并且打印
      that.client.on("message",function(topic,message){
        //message是16进制的Buffered字节流
        console.log(topic)
        // console.log(message)
        let dataFromDev = {}
        try{
          dataFromDev = JSON.parse(message)
          console.log(dataFromDev);
        }catch(error){
          console.log(error)
        }
      })
    })
    
  },
  
}
</script>

<style scoped>
/* 用户界面样式 */
img{
  width: 100%;
  height: 100px;
  border: 0;
}
.user{
  background-color: white;
  box-shadow: 2px 2px 5px 5px rgba(180,180,180,0.5);
  height: 80px;
  display: flex;
  align-items: center;
}
.user img{
  text-align: center;
}
.user .text{
  margin-left: 10px;
}
.user .deposit{
  margin-left: auto;
}
.choose{
  padding: 10px;
}
.combo{
  background-color: white;
  height: 70px;
  display: flex;
  align-items: center;
  margin-top: 5px;
}
.combo .text{
  font-size: 24px;
  font-weight: bold;
  color: #3973d0;
  text-align: center;
  margin-left: 10px;
}
.combo .start{
  margin-left: auto;
}

/* 管理员界面样式 */
.operate{
  margin: 10px;
  font-size: 24px;
}
.container{
  display: flex;
  flex-wrap: wrap;
}
.container .box{
  display: flex;
  background-color: white;
  flex: 1;
  height: 120px;
  border: 2px solid #eee;
  align-items: center;
  border-radius: 10%;
  margin: 10px;
}
.container .text{
  font-size: 24px;
  font-weight: bold;
  margin-left: 20px;
}
</style>
