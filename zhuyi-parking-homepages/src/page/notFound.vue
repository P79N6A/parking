<template>
  <div class="not-found">
    <div class="main">
      <img src="../assets/img_404.png">
      <div style="color: #444;margin-top: 28px;">抱歉，您访问的页面不存在……</div>
      <router-link to="/">返回首页</router-link>
    </div>
  </div>
</template>
<style lang="less" scoped>
  .not-found {
    position: fixed;
    top: 0;
    left: 0;
    right: 0;
    bottom: 0;
    z-index: 101;
    background: #fff;
    .main {
      width: 500px;
      height: 545px;
      position: absolute;
      top: 50%;
      left: 50%;
      transform: translate(-50%, -50%);
      text-align: center;
      img {
        width: 500px;
        height: 376px;
      }
      a {
        display: inline-block;
        width: 220px;
        height: 60px;
        line-height: 60px;
        color: #fff;
        background: #3296FA;
        border-radius: 30px;
        font-size: 20px;
        margin-top:60px;
        &:hover {
          cursor: pointer;
        }
      }
    }
  }
</style>
