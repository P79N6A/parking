<template>
  <router-link :to="'/information/'+ item.id" tar="li" class="news-item">
    <dl>
      <dd>
        <img v-lazy="item.imageUrl">
        <div class="title-line fx">
          <div class="tit-info" style="width: 620px;text-align: left">
            {{item.title}}
          </div>
          <div class="tit-time">
            {{item.creationTime.slice(0,10)}}
          </div>
        </div>
      </dd>
      <dt>
        <span>
          {{item.summary}}
        </span>
      </dt>
    </dl>
  </router-link>
</template>
<script>
export default {
  name: 'singleNews',
  props: ['item']
}
</script>
<style lang="less" scoped>
.news-item {
  height: 380px;
  width: 897px;
  display: inline-block;
  margin-bottom: 20px;
  dd {
    height: 300px;
    position: relative;
    img {
      width: 897px;
      height: 300px;
    }
    .title-line {
      width: 100%;
      height: 60px;
      position: absolute;
      top: 0;
      left: 0;
      background: rgba(34, 34, 34, .5);
      line-height: 60px;
      & > div {
        padding: 0 20px;
        font-size: 20px;
        color: #fff;
      }
      .tit-info {
        float: left;
        width: 580px;
        overflow: hidden;
        text-overflow: ellipsis;
        white-space: nowrap;
      }
      .tit-time {
        float: right;
      }
    }
  }
  dt {
    height: 80px;
    background: #fafafa;
    font-size: 14px;
    color: #222;
    padding: 20px 16px;
    box-sizing: border-box;
    span {
      display: block;
      height: 48px;
      line-height: 24px;
      overflow: hidden;
      text-overflow: ellipsis;
      display: -webkit-box;
      -webkit-line-clamp: 2;
      -webkit-box-orient: vertical;
      text-align: left;
    }
  }
}
</style>
