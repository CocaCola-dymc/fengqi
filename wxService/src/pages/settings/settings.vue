<template>
  <div>
    <div class="container">
      <div class="box">
        <van-icon class="icon" name="comment-o" size="30px"></van-icon>
        <div class="text">用户协议</div>
        <div class="arrow">>&nbsp;&nbsp;&nbsp;</div>
      </div>
      <div class="box">
        <van-icon class="icon" name="question-o" size="30px"></van-icon>
        <div class="text">常见问题</div>
        <div class="arrow">>&nbsp;&nbsp;&nbsp;</div>
      </div>
      <div class="box">
        <van-icon class="icon" name="guide-o" size="30px"></van-icon>
        <div class="text">关于小程序</div>
        <div class="arrow">>&nbsp;&nbsp;&nbsp;</div>
      </div>
    </div>
  </div>
</template>

<script>
// import { formatTime } from '@/utils/index'

export default {
  data () {
    return {

    }
  },
}
</script>

<style scoped>
.box{
  background-color: white;
  height: 60px;
  display: flex;
  align-items: center;
  border-top: 1px solid #ccc
}
.box .icon{
  margin-left: 10px;
}
.box .text{
  margin-left: 10px;
  font-size: 16px;
}
.box .arrow{
  margin-left: auto;
}
</style>
