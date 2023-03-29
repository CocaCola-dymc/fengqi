<template>
  <div>
    <div class="user">
      <div class="text">
        <div class="username">{{ username }}</div>
        <div>普通用户</div>
      </div>
    </div>
    <div class="amount">
      <div>请选择充值金额</div>
    </div>
    <div class="choose">
      <van-cell-group :class="arrayIndex == -1 ? 'custom_active':'custom_normal'">
        <!-- 登录表单 -->
        <van-field
          type="number"
          label="自定义金额"
          placeholder="请输入1~9999的整数"
          v-model="money"
          @blur="checkAmount"
          @focus="customAmount"
          @input="money = $event.mp.detail"
        />
      </van-cell-group>


      <div :class="arrayIndex == index ? 'box_active' : 'box_normal'"  v-for="(item,index) in numArray" :key="index" @click="activeIndex(item,index)">
          <span class="big_text">{{item}}</span>
          <span class="small_text">元</span>
      </div>

    </div>


    <div class="button">
      <van-button round type="primary" icon="certificate" size="large" @click="onDeposit">确认充值</van-button>
    </div>
  </div>
</template>

<script>

const baseUrl = 'http://127.0.0.1:8080'

export default {

  data () {
    return {
      username:'',
      money: '',
      item: 5,        //默认第一个
      arrayIndex: 0,  //默认为第一个
      numArray: [5,10,15,20,50,100],
    }
  },

  methods:{
    customAmount(){
      this.arrayIndex = -1;    //将金额盒子移动到自定义金额
    },
    checkAmount(){
      let amountRegExp = /^[0-9]{1,4}$/
      if(amountRegExp.test(this.money) || this.activeIndex != -1){
        if(this.money>0){   //判断输入的金额是否大于0
          return true;
        }else{
          return false;
        }
      }else{                                          
        return false;
      }
    },

    activeIndex(item,index){
      this.item = item;
      this.arrayIndex = index;
    },

    onDeposit(){
      if(this.arrayIndex != -1){
        this.money = this.item;
      }else{
        this.money = Number(this.money);
      }
      if(this.checkAmount()){
        wx.request({
          url: baseUrl+'/fengqi/doDepositUser',
          method: "POST",
          data:{
            username: this.username,
            money: this.money,
          },
          header: { 'content-type': 'application/x-www-form-urlencoded'},
          success: (res) =>{
            let data = res.data;
            if(data == 1){
              wx.showToast({
                title: '充值成功',
                icon: 'success',
                duration: 2000
              })
            }
            setTimeout(() => {
              wx.reLaunch({
                url: '/pages/index/main',
              })
            }, 2000);
          }
        })
      }else{
          wx.showToast({
            title: '金额应为1~9999',
            icon: 'error',
            duration: 2000
          })
      }
    }
  },

  onLoad(){
    this.username = this.globalData.username;
    // this.globalData.flag_user  = 1;
    // this.globalData.flag_admin = 0;
  }
}

</script>

<style>
.user{
  background-color: white;
  box-shadow: 2px 2px 5px 5px rgba(180,180,180,0.5);
  height: 100px;
  display: flex;
  align-items: center;
  padding-top: 20px;
}
.user .text{
  margin-left: 20px;
}
.user .username{
  font-size: 32px;
  font-weight: bold;
}
.amount{
  padding: 10px;
}
.choose{
  /* background-color: white; */
  display: flex;
  height: 260px;
  flex-wrap: wrap;
  border-radius: 10%;
  margin: 5px;
}
.choose .custom_active{
  width: 100%;
  margin: 10px;
  border: 2px solid lightgreen;
}
.choose .custom_normal{
  width: 100%;
  margin: 10px;
}
.choose .box_active{
  display: flex;
  justify-content: center;
  background-color: white;
  width: 110px;
  height: 70px; 
  margin: 3px;
  align-items: center;
  border-radius: 10%;
  background-color: greenyellow;
  border: 2px solid lightgreen;
}
.choose .box_normal{
  display: flex;
  justify-content: center;
  background-color: white;
  width: 110px;
  height: 70px; 
  margin: 3px;
  align-items: center;
  border-radius: 10%;
  background-color: white;
  border: 2px solid white;
}
.big_text{
  font-size: 24px;
  font-weight: bold;
}
.button{
  margin-top: 50px;
}
</style>
