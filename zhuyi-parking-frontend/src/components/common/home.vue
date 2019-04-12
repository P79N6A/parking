<template>
  <div class='wrap'>
    <v-header></v-header>
    <div class="home-box">
      <v-sidebar></v-sidebar>
      <div class="home-content">
        <transition name="move" mode="out-in"><router-view></router-view></transition>
      </div>
    </div>
  </div>
</template>

<script>
import vHeader from '../common/header.vue'
import vSidebar from '../common/sidebar.vue'
export default {
  name: 'Home',
  components: {
    vHeader,
    vSidebar
  },
  data () {
    return {
      msg: 'Welcome to Your Vue.js App'
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
  .wrap{
    height: 100%;
    min-width: 1200px;
    background-color: #fff;
  }
  .home-box{
    display: flex;
    position: absolute;
    top: 60px;
    right: 0;
    bottom: 0;
    left: 0;
  }
  .home-content{
    flex: 1;
  }
</style>