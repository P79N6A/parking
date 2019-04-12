<template>
    <div class="sidebar">
      <el-menu
        :default-active="onRoutes"
        router
        class="el-menu-vertical-demo"
        background-color="#33485c"
        text-color="#fff"
        active-text-color="#409EFF">
        <template v-for="item in items">
          <template v-if="item.subs">
            <el-submenu :index="item.index">
              <template slot="title">
                <i :class="item.icon"></i>
                <span>{{ item.title }}</span>
              </template>
              <el-menu-item v-for="(subItem,i) in item.subs" :key="i" :index="subItem.index" >{{ subItem.title }}</el-menu-item>
            </el-submenu>
          </template>
          <template v-else>
            <el-menu-item :index="item.index">
              <i :class="item.icon"></i>
              <span slot="title">{{ item.title }}</span>
            </el-menu-item>
          </template>
        </template>
      </el-menu>
    </div>
</template>
<script>
export default {
  name: 'Sidebar',
  data() {
    return {
      items:  [
                {
                    icon: 'el-icon-bell',
                    index: 'home',
                    title: '首页'
                },
                {
                    icon: 'el-icon-menu',
                    index: '2',
                    title: '表格',
                    subs: [
                        {
                            index: 'basetable',
                            title: '基础表格'
                        }//,
                        // {
                        //     index: 'vuetable',
                        //     title: 'Vue表格组件'
                        // }
                    ]
                },
                {
                    icon: 'el-icon-date',
                    index: '3',
                    title: '表单',
                    subs: [
                        {
                            index: 'baseform',
                            title: '基本表单'
                        },
                        {
                            index: 'editor',
                            title: '编辑器'
                        },
                        {
                            index: 'upload',
                            title: '文件上传'
                        }
                    ]
                },
                {
                    icon: 'el-icon-news',
                    index: 'layer',
                    title: '弹层'
                }
                // {
                //     icon: 'el-icon-upload2',
                //     index: 'drag',
                //     title: '拖拽'
                // }
              ]
    }
  },
  computed:{
      onRoutes(){
          return this.$route.path.replace('/','');
      }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style lang="less">
  @import url("../../../style/sidebar.css");
</style>