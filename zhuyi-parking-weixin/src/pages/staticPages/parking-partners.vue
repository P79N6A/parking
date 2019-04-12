<template>
  <div class="parking-box">
    <div class="item-box">
      <div class="title"></div>
      <div class="content"></div>
      <div class="bottom">
        <p class="info">请以【合作招商】+ 合作目标为标题，将联系人、联系方式，车场信息发送到邮箱</p>
        <p class="email">service@zhuyitech.com</p>
        <p class="return">我们会在3个工作日内回复您</p>
      </div>
    </div>
  </div>
</template>
<script>
export default {
  data () {
    return {
      title: '合作招商'
    }
  },
  created () {
    document.title = this.title
  }
}
</script>
<style scoped lang="less">
  .parking-box {
    position: absolute;
    top: 0;
    left: 0;
    right: 0;
    bottom: 0;
    overflow: hidden;
    z-index: 50;
    p {
      padding: 0;
      margin: 0;
    }
    .item-box {
      position: absolute;
      top: 0;
      width: 100%;
      height: 100%;
      background: url("../../assets/static_img/person.png") no-repeat;
      background-size: 100% 100%;
      z-index: 500;
      .title {
        width: 3.35rem;
        height: .57rem;
        background: url("../../assets/static_img/parking_title.png") no-repeat;
        background-size: 100%;
        margin-top: .5rem;
        margin-left: .2rem;
      }
      .content {
        width: 3.35rem;
        height: 1.6rem;
        background: url("../../assets/static_img/person_cont.png") no-repeat;
        background-size: 100%;
        margin-top: .74rem;
        margin-left: .2rem;
      }
      .bottom {
        position: absolute;
        bottom: .33rem;
        left: .2rem;
        width: 3.33rem;
        height: .95rem;
        text-align: left;
        .info {
          height: .32rem;
          font-size: .12rem;
          // line-height: .20rem;
          color: rgba(255, 255, 255, .8);
        }
        .email {
          height: .24rem;
          font-size: .22rem;
          margin-top: .12rem;
          color: rgba(255, 255, 255, 1);
        }
        .return {
          height: .12rem;
          font-size: .12rem;
          margin-top: .15rem;
          color: rgba(255, 255, 255, .5);
          text-align: right;
        }
      }
    }
    .item-box::before {
      content: '';
      display: table;
    }
  }
</style>
