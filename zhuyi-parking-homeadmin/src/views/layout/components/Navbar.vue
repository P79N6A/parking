<template>
  <el-menu class="navbar" mode="horizontal">
    <hamburger class="hamburger-container" :toggleClick="toggleSideBar" :isActive="sidebar.opened"></hamburger>
    <div class="avatar-wrapper">
      <span @click="logout">退出</span>
    </div>
  </el-menu>
</template>

<script>
import { mapGetters } from 'vuex'
import request from '@/utils/request'
import Hamburger from '@/components/Hamburger'

export default {
  components: { Hamburger },
  computed: {
    ...mapGetters([
      'sidebar'
    ])
  },
  methods: {
    toggleSideBar() {
      this.$store.dispatch('ToggleSideBar')
    },
    logout() {
      request({ url: '/portal/user/exitLogin', method: 'post' }).then((response) => {
        if (response.data.code === 1) {
          this.$router.push({ path: '/' })
          // location.reload() // 为了重新实例化vue-router对象 避免bug
        }
      })
    }
  }
}
</script>

<style rel="stylesheet/scss" lang="scss" scoped>
.navbar {
  height: 50px;
  line-height: 50px;
  border-radius: 0px !important;
  .hamburger-container {
    line-height: 58px;
    height: 50px;
    float: left;
    padding: 0 20px;
  }
  .avatar-wrapper {
    cursor: pointer;
    position: relative;
    span {
      position: absolute;
      right: 30px;
    }
  }
}
</style>

