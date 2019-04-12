<template>
    <div class="header">
      <div class="left">
        <img src="../../../static/img/ring.png">
      </div>
      <div class="head-content">
        <span>{{ title }}</span>
        <div class="user-box">
          <div class="head-img"><img src="../../../static/img/head.jpg" alt=""></div>
          <div class="user-name">王小丫</div>
        </div>
      </div>
    </div>
</template>
<script>
export default {
  name: 'Header',
  computed: {
    title () {
      return this.$store.getters.title
    }
  },
  methods: {
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style src="../../../style/header.less" lang="less" scoped>
</style>