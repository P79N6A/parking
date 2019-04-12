<template>
  <div class="single-box">
    <img v-lazy="item.imageUrl" >
    <div class="addr">
      {{item.name}}
    </div>
    <div class="desc-cover" v-if="item.content">
      <div>
        {{item.content}}
      </div>
    </div>
  </div>
</template>
<script>
export default {
  props: ['item'],
  data() {
    return {}
  }
}
</script>
<style lang="less" scoped>
  .single-box {
    width: 380px;
    height: 520px;
    position: relative;
    display: inline-block;
    img {
      display: block;
      width: 380px;
      height: 460px;
    }
    .addr {
      width: 100%;
      height: 60px;
      box-sizing: border-box;
      text-align: center;
      padding: 0 20px;
      background: #FAFAFA;
      font-size: 16px;
      line-height: 60px;
      overflow: hidden;
      text-overflow: ellipsis;
      white-space: nowrap;
    }
    .desc-cover {
      width: 380px;
      height: 460px;
      position: absolute;
      display: none;
      left: 0;
      top: 0;
      background: rgba(0, 0, 0, .7);
      color: #fff;
      font-size: 16px;
      line-height: 32px;
      text-align: left;
      div {
        width: 320px;
        height: 380px;
        overflow: hidden;
        text-overflow: ellipsis;
        -webkit-line-clamp: 12;
        -webkit-box-orient: vertical;
        margin: 35px 30px;
      }
    }
    &:hover .desc-cover {
      display: block;
      cursor: pointer;
    }
  }
</style>
