<template>
  <div class="nav-bar fx">
    <el-menu
      :default-active="$route.path"
      class="el-menu-demo"
      mode="horizontal"
      @select="handleSelect"
      active-text-color="#3296FA">
      <el-menu-item index="/">
        <router-link to="/">
          首页
          <i></i>
        </router-link>
      </el-menu-item>
      <el-menu-item index="/project">
        <router-link to="/project">
          解决方案
          <i></i>
        </router-link>
      </el-menu-item>
      <el-menu-item index="/app">
        <router-link to="/app">
          任意停车APP
          <i></i>
        </router-link>
      </el-menu-item>
      <el-menu-item index="/case">
        <router-link to="/case">
          案例
          <i></i>
        </router-link>
      </el-menu-item>
      <el-menu-item index="/cooperation">
        <router-link to="/cooperation">
          合作
          <i></i>
        </router-link>
      </el-menu-item>
      <el-menu-item index="/aboutUs">
        <router-link to="/aboutUs">
          我们
          <i></i>
        </router-link>
      </el-menu-item>
      <!-- <el-menu-item index="/joinUs">
        <router-link to="/joinUs">
          招聘
          <i></i>
        </router-link>
      </el-menu-item> -->
      <div class="admin-site fr">
        <div class="site-btn" @click.stop="turnToAdminSite">商户平台</div>
      </div>
    </el-menu>
  </div>
</template>
<script>
export default {
  name: 'navBar',
  data() {
    return {
      activeIndex: '/'
    }
  },
  methods: {
    handleSelect(key, keyPath) {
      this.activeIndex = keyPath[0]
    },
    turnToAdminSite() {
      alert('即将上线,敬请期待!')
      // window.open('http://admin.xxx')
    }
  }
}
</script>
<style lang="less" scoped>
.nav-bar {
  height: 80px;
  display: inline-block;
  font-weight: normal;
  li {
    padding: 0;
    height: 80px;
    line-height: 80px;
    font-size: 20px;
    &.is-active i {
      background: #3296FA;
    }
  }
  li:not(:last-child) {
    margin-right: 50px;
  }
  a {
    display: inline-block;
    // width: 40px;
    height: 80px;
    position: relative;
    i {
      display: block;
      width: 100%;
      height: 4px;
      position: absolute;
      top: 66px;
      left: 0;
    }
  }
  .el-menu--horizontal {
    border-bottom: none;
  }
  .admin-site {
    height: 80px;
    width: 130px;
    line-height: 80px;
    font-size: 18px;
    color: #fff;
    .site-btn {
      display: inline-block;
      width: 110px;
      margin-left: 20px;
      margin-top: 22px;
      border-radius: 2px;
      height: 40px;
      background: #46A0FA;
      line-height: 40px;
      &:hover {
        cursor: pointer;
      }
    }
  }
}
</style>
