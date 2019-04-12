<template>
  <div class="scan-in"></div>
</template>
<script>
import axios from '@/utils/request'
export default {
  name: 'scanIn',
  data () {
    return {}
  },
  created () {
    this.linkToPage()
  },
  methods: {
    linkToPage () {
      axios({url: '/platform/trade/jsapiconfig', method: 'post', params: {}}).then(res => {
        if (res.data.code === 1) {
          let id = this.$route.params.id
          let appid = res.data.data.appId
          // vue 版
          // let url = `https://open.weixin.qq.com/connect/oauth2/authorize?appid=${appid}&redirect_uri=http%3a%2f%2fwx.zhuyitech.com%2fmp%2fscanQuery&response_type=code&scope=snsapi_userinfo&state=${id}&connect_redirect=1#wechat_redirect`
          // jquery 版
          let url = `https://open.weixin.qq.com/connect/oauth2/authorize?appid=${appid}&redirect_uri=http%3a%2f%2fwx.zhuyitech.com%2fmp%2fscanIn.html&response_type=code&scope=snsapi_userinfo&state=${id}&connect_redirect=1#wechat_redirect`
          window.open(url, '_self')
        } else {
          this.$notify({
            title: '失败',
            message: res.data.message,
            type: 'error',
            duration: 3000
          })
        }
      })
    }
  }
}
</script>
<style scoped lang="less">
  .scan-in {
    background: #fff;
  }
</style>
