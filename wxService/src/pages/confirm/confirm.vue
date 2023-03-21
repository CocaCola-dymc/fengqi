<template>
  <div class="container">
    <div class="combo">
      <van-image class="img" width="60px" height="60px" src="/static/images/cdx.jpeg"></van-image>
      <div>
        <div class="text">已选中的套餐</div>
        <div class="text1">1￥ / 10秒钟</div>
      </div>
    </div>
    <div class="choose">
      <div>请选择充电口</div>
    </div>
    <div class="charge">
      <div class="box">
        <van-image class="image" width="100px" height="100px" src="/static/images/cdx.png"></van-image>
        <div>1号充电口</div>
      </div>
      <div class="box">
        <van-image class="image" width="100px" height="100px" src="/static/images/cdx.png"></van-image>
        <div>2号充电口</div>
      </div>
      <div class="box">
        <van-image class="image" width="100px" height="100px" src="/static/images/cdx.png"></van-image>
        <div>3号充电口</div>
      </div>
      <div class="box">
        <van-image class="image" width="100px" height="100px" src="/static/images/cdx.png"></van-image>
        <div>4号充电口</div>
      </div>
      <div class="box">
        <van-image class="image" width="100px" height="100px" src="/static/images/cdx.png"></van-image>
        <div>5号充电口</div>
      </div>
      <div class="box">
        <van-image class="image" width="100px" height="100px" src="/static/images/cdx.png"></van-image>
        <div>6号充电口</div>
      </div>
      <div class="box">
        <van-image class="image" width="100px" height="100px" src="/static/images/cdx.png"></van-image>
        <div>7号充电口</div>
      </div>
      <div class="box">
        <van-image class="image" width="100px" height="100px" src="/static/images/cdx.png"></van-image>
        <div>8号充电口</div>
      </div>
      <div class="box">
        <van-image class="image" width="100px" height="100px" src="/static/images/cdx.png"></van-image>
        <div>9号充电口</div>
      </div>
    </div>
    <div class="deposit">
      <van-button icon="passed" type="primary" round size="large">确认</van-button>
    </div>
  </div>
</template>

<script>

export default {
  data () {
    return {

    }
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
  height: 390px;
}
.charge .box{
  display: flex;
  background-color: white;
  flex: 1;
  height: 130px;
  border: 2px solid #eee;
  align-items: center;
  flex-direction: column;
}
.box .image{
  text-align: center;
}
.deposit{
  padding-top: 100px;
}
</style>
