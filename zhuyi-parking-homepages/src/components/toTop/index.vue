<template>
  <div class="to-top" @click.prevent="toTop"></div>
</template>
<script>
export default {
  name: 'toTop',
  data() {
    return {
      scrollTopDistance: 0,
      timer: '',
    }
  },
  methods: {
    toTop() {
      const currScroll = this.scrollTopDistance
      clearInterval(this.timer)
      this.timer = setInterval(() => {
        let speed = (0 - currScroll) / 5
        speed = speed > 0 ? Math.ceil(speed) : Math.floor(speed)
        if (this.scrollTopDistance <= 0) {
          clearInterval(this.timer)
          this.show = false
        }
        if (document.body.scrollTop) {
          document.body.scrollTop = this.scrollTopDistance + speed
        } else {
          document.documentElement.scrollTop = this.scrollTopDistance + speed
        }
        document.documentElement.scrollTop = this.scrollTopDistance + speed
        // console.log(document.body.scrollTop)
      }, 1 / 60)
    },
  }
}
</script>
<style lang="less" scoped>
  .to-top {
    width: 66px;
    height: 66px;
    background: url("../../assets/icon_totop.png");
    position: fixed;
    bottom: 230px;
    right: 230px;
    cursor: pointer;
    z-index: 500;
  }
</style>
