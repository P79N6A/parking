<template>
  <div class="index-content">
    <div class="date-box">
      <div class="data-logo"><img src="../../../static/img/now.png" alt=""></div>
      <div class="data-detail" >{{ time }}</div>
    </div>
    <p class="title">常用功能</p>
    <div class="pass-box">
      <div class="pass-item" v-for="(menu,i) in menuArr" :key="i" @click="goPage(menu.path)">
        <div class="pass-icon" :class="menu.bg"><i :class="menu.icon"></i></div>
        <div class="pass-detail">
          <p class="name">{{ menu.title }}</p>
          <p class="tips">点击进入{{ menu.title }}页面</p>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import {Utils} from '../../common/util.js'
let utils = new Utils()
export default {
  name: 'index',
  components: {
  },
  data () {
    return {
      time: '1121',
      menuArr: [
        {
          icon: 'el-icon-picture',
          bg: 'bg1',
          title: '上传图片',
          path: 'upload'
        },
        {
          icon: 'el-icon-document',
          bg: 'bg2',
          title: '基础表格',
          path: 'basetable'
        },
        {
          icon: 'el-icon-edit-outline',
          bg: 'bg3',
          title: '基础表单',
          path: 'baseform'
        },
        {
          icon: 'el-icon-edit',
          bg: 'bg4',
          title: '编辑商品',
          path: 'editor'
        }
      ]
    }
  },
  methods: {
    goPage(path){
      this.$router.push(path)
    }
  },
  created(){
    let self = this
    self.time = utils.getTime ()
    function change(){
      self.time = utils.getTime ()
      setTimeout ( change , 1000)
    }
    change()
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style src="../../../style/index.less" lang="less" >
</style>
