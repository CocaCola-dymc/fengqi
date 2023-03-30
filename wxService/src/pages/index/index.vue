<template>
  <div>
    <div v-if="flag_user">
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
        <div>{{ username }}</div>
      </div>
      <div class="container">
        <div class="box">
          <van-image class="image" width="64px" height="64px" src="/static/images/wendu.png"></van-image>
          <div class="text">
            <div>温度</div>
            <div>{{ temp }}°C</div>
          </div>
        </div>
        <div class="box">
          <van-image class="image" width="64px" height="64px" src="/static/images/deng.png"></van-image>
          <div class="text">
            <div>车场灯</div>
            <van-switch :checked="checked" @change="onChange" />
          </div>
        </div>
        <div class="box" @click="onList">
          <van-image class="image" width="64px" height="64px" src="/static/images/list.png"></van-image>
          <div class="text">
            <div>用户列表</div>
          </div>
        </div>
        <div class="box" @click="chargeRecord">
          <van-image class="image" width="64px" height="64px" src="/static/images/chongdian.png"></van-image>
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

const pubLed  =  "led_topic";         //发布灯主题
const subTemp = "temp_topic";         //订阅温度主题


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
      data: '',
      checked: null,
      temp: 0,
    };
  },

  components: {},

  methods: {
    onDeposit(){
      wx.navigateTo({
        url: '/pages/deposit/main',
      })
    },

    onStart(item,index){
      this.item = item;
      wx.navigateTo({
        //传入用户名username、套餐名item、余额money
        url: `/pages/combo/main?item=${this.item}&money=${this.money}`,   
      })
    },

    onMine(){   //跳转到mine.vue
      wx.reLaunch({
        url: '/pages/mine/main', 
      })
    },

    //小程序控制开灯关灯
    onChange(){     //改变灯的开关状态
      let flag_led;
      this.checked = !this.checked; 
      if(this.checked){
        flag_led = {"type":"led","flag":"on"};
      }else{
        flag_led = {"type":"led","flag":"off"};
      }
      let msg = JSON.stringify(flag_led)
      //发布话题
      this.client.publish(pubLed,msg,function(err){
        if(!err){
          console.log('成功发布pubLed')
        }
      })
    },

    //跳转到用户列表
    onList(){
      wx.navigateTo({
        url: '/pages/list/main',
      })
    },

    chargeRecord(){
      wx.navigateTo({
        url: '/pages/charge/main?active=1',   // active=1,切换到内容2
      })
    },
  },

  onLoad(){
    //获取全局变量
    //全局变量为Number类型,转换成Boolean类型,进行判断
    this.flag_user = Boolean(this.globalData.flag_user);
    this.flag_admin = true
    // this.flag_admin = Boolean(this.globalData.flag_admin);
    // this.username = this.globalData.username;
    this.username = "tony";

    if(this.flag_user){ //如果是普通用户
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
    }

    if(this.flag_admin){ //如果是管理员

    }

    var that = this 
    //连接MQTT
    that.client = connect(mqttUrl)
    //连接成功时返回的信息
    that.client.on("connect",function (){
      console.log("connect ok")
    })
    //订阅温度主题
    that.client.subscribe(subTemp,function(err){
      if(!err){
        console.log('成功订阅subTemp')
      }
    })
    //接收topic的信息并且打印
    that.client.on("message",(topic,message)=>{
      //message是16进制的Buffered字节流
      // console.log(topic)
      let temp = message.toString()
      // console.log(message.toString())
      this.temp = temp;
      // let dataFromDev = {}
      // dataFromDev = JSON.parse(message)
      // console.log(dataFromDev);
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
  font-size: 20px;
  font-weight: bold;
  margin-left: 12px;
}
</style>
