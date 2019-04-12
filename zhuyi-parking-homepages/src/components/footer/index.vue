<template>
  <div class="footer">
    <div class="content fx">
      <ul class="left fl">
        <li class="f-title">任意停车，致力于智慧城市建设</li>
        <li class="line2">公司地址：浙江省杭州市富阳区春秋北路591号二楼</li>
        <li class="line3">技术支持：杭州逐一科技有限公司</li>
        <li class="line4">©2018 . ALL Rights Reserved Zhuyi Technology CO.，LTD.杭州逐一科技有限公司</li>
      </ul>
      <ul class="right fr">
        <li class="f-title">加盟电话：0571-63106805</li>
        <li class="line2">全国智慧车场咨询热线：4008-872-866</li>
        <li class="line3">E-mail：service@zhuyitech.com</li>
        <li class="line4">浙ICP备：浙18002663</li>
      </ul>
    </div>
  </div>
</template>
<script>
export default {
  name: 'Footer'
}
</script>
<style lang="less">
.footer {
  height: 260px;
  background: #2C3039;
  color: #fff;
  .content {
    width: 1200px;
    height: 100%;
    display: inline-block;
    ul {
      text-align: left;
      font-size: 16px;
    }
    ul.left{
      margin-left: 10px;
    }
    ul.right{
      margin-right: 10px;
    }
    .f-title {
      margin-top: 51px;
      font-size: 30px;
      font-weight: bold;
    }
    .line2 {
      margin-top: 35px;
    }
    .line3 {
      margin-top: 15px;
    }
    .line4 {
      margin-top: 15px;
    }
  }
}
</style>
