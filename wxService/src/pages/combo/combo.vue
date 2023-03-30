<template>
  <div class="container">
    <div class="combo">
      <van-image class="img" width="60px" height="60px" src="/static/images/cdx.jpeg"></van-image>
      <div>
        <div class="text">已选中的套餐</div>
        <div class="text1">{{ combo }}</div>
      </div>
    </div>
    <div class="choose">
      <div>请选择充电口</div>
    </div>
    <div class="charge">
      <div :class="arrayIndex == index ? 'box_active':'box_normal'" v-for="(item,index) in numArray" :key="index" @click="activeIndex(item,index)">
        <van-image class="image" width="100px" height="100px" src="/static/images/cdx.png"></van-image>
        <div>{{item}}号充电口</div>
      </div>
    </div>
    <div class="deposit">
      <van-button icon="passed" type="primary" round size="large" @click="onConfirm">确认</van-button>
    </div>
  </div>
</template>

<script>
import { connect } from "mqtt";

const mqttHost = "106.55.60.213"
const mqttPort = "8083";
const mqttUrl = `wx://${mqttHost}:${mqttPort}/mqtt`;
const baseUrl = 'http://127.0.0.1:8080'

const pubPort  =  "port_topic";         //发布套餐主题

export default {
  data () {
    return {
      client:{},
      info:{},
      combo: '',        //初始化套餐
      arrayIndex: 0,    //初始化索引
      item: 1,          //默认为1号端口
      numArray: [1,2,3,4,5,6,7,8,9],    //充电口
    }
  },

  methods:{
    activeIndex(item,index){
      this.item = item;
      this.arrayIndex = index;
    },
    
    onConfirm(){
      let username = this.username;
      let item = this.item;
      let combo = Number(this.combo.slice(0,1))  //获取套餐的第一个字符,也就是套餐的金额,转换成Number
      let money = Number(this.money);            //获取用户的余额
      let time = Number(this.combo.slice(5,7))   //获取套餐的时间
      let comboName = this.combo;                //获取套餐全名
      let client = this.client;
      wx.showModal({
        title: '提示',
        content: '确定支付此套餐吗?',
        success(res){
          if(res.confirm){
            if(money >= combo){   //判断如果余额大于等于套餐金额
              wx.request({          //开始执行http请求
                url: baseUrl+`/fengqi/doChargeUser`,
                methods: "POST",
                data:{
                  username: username,
                  money: combo,
                },
                header: { 'content-type': 'application/x-www-form-urlencoded'},
                success: (res) =>{
                  let data = res.data;
                  if(data == 1){
                    wx.showToast({
                      title: '支付成功',
                      icon: 'success',
                      duration: 2000
                    })
                    setTimeout(() => {
                      wx.navigateTo({
                        url: `/pages/charge/main?combo=${comboName}&time=${time}&flag_charge=1`
                      })
                    }, 2000);
                    
                    //发布套餐主题
                    let message = {"type":item,"flag":time}
                    let msg = JSON.stringify(message);
                    console.log(msg);
                    //发布话题
                    client.publish(pubPort,msg,function(err){
                      if(!err){
                        console.log('成功发布pubPort')
                      }
                    })
                  }
                }
              })
              wx.request({    //将充电记录存入数据库
                url: baseUrl+'/fengqi/doInsertLog',
                data:{
                  username: username,
                  combo: comboName,
                },
                header: { 'content-type': 'application/x-www-form-urlencoded'},
              })
            }else{              //判断如果余额小于套餐金额
              wx.showModal({
                title: '提示',
                content: '余额不足,请前往支付',
                success(res){
                  if(res.confirm){
                    wx.navigateTo({
                      url: `/pages/deposit/main`,
                    });  
                  }
                }
              })
            }
          }else if(res.cancel){
            console.log("已取消支付")
          }
        }
      })
    }
  },
  
  onLoad(option){
    // this.username = this.globalData.username;
    this.username = "tony"
    this.combo = option.item;
    this.money = option.money;

    //请求mqtt发送,开启硬件的充电功能
    // var that = this 
    //连接MQTT
    this.client = connect(mqttUrl)
    //连接成功时返回的信息
    this.client.on("connect",function (){
      console.log("connect ok")
    })
  },
}
</script>

<style scoped>
.combo{
  background-color: white;
  height: 70px;
  display: flex;
  align-items: center;
  margin-top: 5px;
}
.combo .img{
  text-align: center;
  margin-left: 20px;
}
.combo .text{
  margin-left: 10px;
}
.combo .text1{
  font-size: 24px;
  font-weight: bold;
  color: #3973d0;
  text-align: center;
  margin-left: 10px;
}
.choose{
  padding: 10px;
}
.charge{
  display: flex;
  flex-wrap: wrap;
  height: 420px;
}
.charge .box_active{
  display: flex;
  background-color: white;
  flex: 1;
  height: 130px;
  border: 2px solid green;
  align-items: center;
  flex-direction: column;
  margin: 2px;
}
.charge .box_normal{
  display: flex;
  background-color: white;
  flex: 1;
  height: 130px;
  border: 2px solid white;
  align-items: center;
  flex-direction: column;
  margin: 2px;
}
.box .image{
  text-align: center;
}

</style>
