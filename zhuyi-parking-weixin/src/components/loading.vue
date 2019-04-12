<template>
  <div class="loading-el">{{loadingStatus}}</div>
</template>
<script>
export default {
  name: 'loadingImg',
  props: ['loadingStatus']
}
</script>
<style scoped lang="less">
.loading-el {
  position: fixed;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  color: #fff;
  z-index: 66;
  background: rgba(77, 77, 77, .8);
  &::before {
    content: '';
    display: block;
    width: .38rem;
    height: .38rem;
    position: absolute;
    top: 50%;
    left: 50%;
    background: url("../assets/loading.gif") no-repeat;
    background-size: 100% 100%;
    transform: translate(-50%,-50%);
  }
}
</style>
