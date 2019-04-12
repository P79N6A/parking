<template>
  <ul>
    <li v-for="index in length" :key="index" :class="'item-' + index">{{plate|plateFilter(index)}}</li>
  </ul>
</template>

<script>
export default {
  name: 'plate-input',
  props: {
    length: {
      type: Number,
      required: true
    },
    plate: {
      type: String,
      required: true
    }
  },
  filters: {
    plateFilter (plate, idx) {
      return plate.slice(idx - 1, idx)
    }
  }
}
</script>

<style scoped lang="less">
 li {
   float: left;
   width: .32rem;
   height: .45rem;
   background: #f9f9f9;
   font-size: .16rem;
   color: #222;
   line-height: .45rem;
   text-align: center;
   &:not(:last-child) {
     margin-right: .02rem;
   }
 }
</style>
