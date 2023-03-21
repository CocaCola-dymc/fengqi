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
      <van-cell-group class="custom">
        <!-- 登录表单 -->
        <van-field
          type="number"
          label="自定义金额"
          placeholder="请输入充值金额"
          v-model="amount"
          @blur.native.capture="checkAmount"
        />
      </van-cell-group>
      <div class="box">
        <span class="big_text">5</span>
        <span class="small_text">元</span>
      </div>
      <div class="box">
        <span class="big_text">10</span>
        <span class="small_text">元</span>
      </div>
      <div class="box">
        <span class="big_text">20</span>
        <span class="small_text">元</span>
      </div>
      <div class="box">
        <span class="big_text">50</span>
        <span class="small_text">元</span>
      </div>
      <div class="box">
        <span class="big_text">100</span>
        <span class="small_text">元</span>
      </div>
    </div>
    <div class="button">
      <van-button round type="primary" icon="certificate" size="large">确认充值</van-button>
    </div>
  </div>
</template>

<script>

export default {

  data () {
    return {
      username:'kunkun'
    }
  },
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
  background-color: white;
  display: flex;
  height: 260px;
  flex-wrap: wrap;
  border-radius: 10%;
  margin: 5px;
}
.choose .custom{
  width: 100%;
  margin: 15px;
  margin-bottom: 0;
}
.choose .box{
  display: flex;
  justify-content: center;
  background-color: white;
  width: 110px;
  height: 70px; 
  margin: 3px;
  border: 2px solid #666;
  align-items: center;
  border-radius: 10%;
}
.big_text{
  font-size: 24px;
  font-weight: bold;
}
.button{
  margin-top: 50px;
}
</style>
