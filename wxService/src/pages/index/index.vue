<template>
  <div class="wrapper">
    <!-- <image width="100" height="auto" src="../../static/images/qihuo.png"></image> -->
    <!-- <button @click="doorOpen">开门</button>
    <van-button type="primary" @click="queryUser">查询</van-button> -->
    
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
      client:{},
      info:{}
    };
  },

  components: {},

  methods: {
    doorOpen(){
      var that = this;
      console.log('开门')
      that.client.publish(publish_Topic,"1",function(err){
        if(!err){
          console.log("门已开启")
        }
      })  
    },

    queryUser(){
      console.log("查询")
      wx.request({
        url: baseUrl+'/user/doFindUser',
        method: "GET",
        // data: {
        //   username: username,
        //   password: password,
        //   createdTime: createdTime
        // },
        //成功回调函数
        success: (res) => {
          let data = res.data
          console.log(data)
          // //获取返回值列表
          // list = res.data.data
          // //返回值设置至data
          // this.setData({
          //   list: list
          // })
        }
      })
    }
  },

  onLoad(){
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
    
  }
}
</script>

<style scoped>
.wrapper{
  padding: 15px;
  
}
</style>
