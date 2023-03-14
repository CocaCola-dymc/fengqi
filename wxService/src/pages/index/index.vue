<template>
  <div class="wrapper">
    <button @click="clickbutton">刷卡</button>
  </div>
</template>

<script>
import { connect } from "mqtt";

const mqttHost = "106.55.60.213"
const mqttPort = "8083";
const mqttUrl = `wx://${mqttHost}:${mqttPort}/mqtt`;

const publish_Topic = "door_topic";
const subscribe_Topic = "card_topic";

export default {
  data () {
    return {
      client:{}
    };
  },

  components: {
  },

  methods: {
    clickbutton(){
      var that = this;
      console.log('开门')
      that.client.publish(publish_Topic,"1",function(err){
        if(!err){
          console.log("门已开启")
        }
      })
    }
  },

  onShow(){
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
  background-color: yellow;
}
</style>
