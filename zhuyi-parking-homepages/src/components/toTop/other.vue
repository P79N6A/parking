<template>
  <div class="to-top" @click.prevent="toTop"></div>
</template>
<script>
export default {
  name: 'toTop',
  props: ['dom'],
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
        this.dom.scrollTop = this.scrollTopDistance + speed
        // console.log(this.dom.scrollTop)
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
