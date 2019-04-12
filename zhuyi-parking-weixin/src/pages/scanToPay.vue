<template>
  <div class="scan-to-pay">
    <transition name="fade">
      <loadingImg v-if="loading" :loadingStatus="loadingStatus"></loadingImg>
    </transition>
    <div class="banner"></div>
    <div class="plate-number-box fx">
      <div class="plate-number">
        <template v-if="plateType !== '6'">
          <label for="label1" class="plate-1">
            <plateInput :length=7 :plate="plateNumber"></plateInput>
          </label>
          <input id="label1" type="text" v-model.trim="plateNumber" maxlength="7">
        </template>
        <template v-else>
          <label for="label2" class="plate-6">
            <plateInput :length=8 :plate="plateNumber"></plateInput>
          </label>
          <input id="label2" type="text" v-model.trim="plateNumber" maxlength="8">
        </template>
      </div>
      <div class="plate-type" @click="toPitchType">{{plateType|plateTypeFilter}}</div>
    </div>
    <div class="lookup-btn" @click="getBillList">查 询</div>
    <div class="plate-type-box" v-if="plateTypeBox" @click="plateTypeBox = !plateTypeBox">
      <ul>
        <li v-for="(item, idx) in plateTypeList" :key="idx"
            :class="['item' + idx, {'active': plateType === item.value}]"
            @click.stop="selectPlateType(item.value)"
        >
          {{item.value|plateTypeFilter}}
        </li>
      </ul>
    </div>
  </div>
</template>
<script>
import axios from '@/utils/request'
import {getPlateColor, login} from '@/utils/api'
import msg from '@/utils/notification'
import loadingImg from '@/components/loading'
import plateInput from '@/components/plateInput'
export default {
  name: 'scanToPay',
  components: {loadingImg, plateInput},
  data () {
    return {
      loading: true,
      loadingStatus: '',
      title: '快捷查询',
      plateType: localStorage.getItem('queryPlateColor') || '1', // 默认车牌颜色
      plateTypeList: [], // 车牌种类
      plateTypeBox: false,
      plateNumber: localStorage.getItem('plateNumber') || ''
    }
  },
  created () {
    document.title = this.title
    if (this.$store.getters.userInfo.accessToken) {
      this.loading = false
    } else {
      this.getCode()
      localStorage.setItem('userState', this.$route.query.state)
    }
    this.getPlateTypeList()
  },
  methods: {
    getPlateTypeList () {
      getPlateColor().then(res => {
        if (res.data.code === 1) {
          this.plateTypeList = res.data.items
        } else {
          msg('', '', res.data.message)
        }
      })
    },
    getCode () {
      if (this.$route.query.code) {
        let tpl1 = {code: this.$route.query.code}

        // 上线待清除
        // this.loadingStatus = 'getWechatInfoJsapi_1'

        axios({url: '/platform/security/getWechatInfoJsapi', method: 'post', params: tpl1}).then(res => {
          if (res.data.code === 1) {
            let data = res.data.data
            this.$store.dispatch('saveUserInfo', data)
            localStorage.setItem('userId', data.openId)
            let tpl2 = {
              openId: data.openId,
              nickName: data.nickName,
              sex: data.sex,
              province: data.province,
              city: data.city,
              country: data.country,
              headImgUrl: data.headimgurl,
              unionid: data.unionid
            }

            // 上线待清除
            // this.loadingStatus = 'jsapiRegisterByScanQR_2'

            axios({url: '/platform/security/jsapiRegisterByScanQR', method: 'post', params: tpl2}).then(res => {
              if (res.data.code === 1 || res.data.code === 10810) {
                let tpl3 = {account: data.unionid, terminateType: 3, loginType: 3}

                // 上线待清除
                // this.loadingStatus = 'login_3'

                login(tpl3).then(res => {
                  if (res.data.code === 1) {
                    this.loading = false
                    let fullInfo = this.$store.getters.userInfo
                    Object.assign(fullInfo, res.data.data)
                    this.$store.dispatch('saveUserInfo', fullInfo)
                  } else {
                    msg('', '', res.data.message)
                  }
                })
              } else {
                /** 404 路由 */
                msg('', '', res.data.message)
                // this.$router.replace({name: 'notFound'})
              }
            })
          } else {
            /** 404 路由 */
            msg('', '', res.data.message)
            // this.$router.replace({name: 'notFound'})
          }
        })
      } else if (!this.$store.getters.userInfo.openId) {
        msg('', '', '请在公众号："逐一科技"中操作!')
      }
    },
    toPitchType () {
      this.plateTypeBox = true
    },
    selectPlateType (n) {
      this.plateType = n
      this.plateTypeBox = false
      if (n !== '6' && this.plateNumber.length > 7) {
        this.plateNumber = this.plateNumber.slice(0, 7)
      }
    },
    slicePlateNumber () {
      this.plateNumber = this.plateNumber.slice(0, -1)
    },
    getBillList () {
      if ((this.plateType !== '6' && this.plateNumber.length === 7) || (this.plateType === '6' && this.plateNumber.length === 8)) {
        localStorage.setItem('plateNumber', this.plateNumber.toUpperCase())
        localStorage.setItem('queryPlateColor', this.plateType)
        /** 账单查询列表 路由 */
        this.$router.push({name: 'scanQueryList'})
      } else {
        msg('', '', '车牌不完整')
      }
    }
  },
  filters: {
    plateTypeFilter (type) {
      const map = {
        '1': '蓝牌',
        '2': '黄牌',
        '6': '新能源'
      }
      return map[type]
    }
  }
}
</script>
<style scoped lang="less">
  .scan-to-pay {
    background: #fafafa;
    position: absolute;
    top: 0;
    left: 0;
    right: 0;
    bottom: 0;
    .banner {
      width: 100%;
      height: 2.05rem;
      background: url("../assets/billQuery/zdBanner.png") no-repeat;
      background-size: 100% 100%;
    }
    .plate-number-box {
      width: 3.55rem;
      height: .63rem;
      position: absolute;
      top: 1.8rem;
      left: .1rem;
      font-size: .16rem;
      background: #fff;
      border-radius: .06rem;
      box-shadow: .01rem .04rem .18rem rgba(55, 119, 215, .15),-.01rem -.04rem .18rem rgba(55, 119, 215, .15);
    }
    .plate-area {
      width: .8rem;
      height: 100%;
      line-height: .63rem;
      float: left;
      letter-spacing: .08rem;
      &::before {
        content: '';
        display: block;
        width: .1rem;
        height: .1rem;
        position: absolute;
        top: .27rem;
        left: .65rem;
        background: url("../assets/billQuery/icon_triangle.png") no-repeat;
        background-size: 100% 100%;
      }
    }
    .plate-number {
      width: 2.78rem;
      height: 100%;
      line-height: .63rem;
      font-size: .16rem;
      float: left;
      padding-left: .2rem;
      box-sizing: border-box;
      text-align: left;
      input {
        outline: none;
        border: none;
        height: 80%;
        width: 2.2rem;
        font-size: .16rem;
        position: absolute;
        left: -10000px;
        top: -10000px;
      }
    }
    .plate-type {
      width: .55rem;
      height: 100%;
      line-height: .63rem;
      float: left;
      font-size: .14rem;
      &::before {
        content: '';
        display: block;
        width: .1rem;
        height: .1rem;
        position: absolute;
        top: .27rem;
        left: 3.35rem;
        background: url("../assets/billQuery/icon_triangle.png") no-repeat;
        background-size: 100% 100%;
      }
    }
    .keyboard {
      position: fixed;
      z-index: 10;
      bottom: 0;
      width: 100%;
      height: 2.45rem;
      .cancel {
        width: 100%;
        height: .5rem;
        line-height: .5rem;
        background: #fff;
        text-align: left;
        padding-left: .2rem;
        box-sizing: border-box;
        color: #222;
        font-size: .16rem;
        box-shadow: .01rem .04rem .06rem rgba(55, 119, 215, .15),-.01rem -.04rem .06rem rgba(55, 119, 215, .15);
      }
    }
    .lookup-btn {
      width: 3.4rem;
      height: .5rem;
      line-height: .5rem;
      color: #fff;
      font-size: .16rem;
      background: #3296FA;
      border-radius: .25rem;
      margin: .97rem .18rem 0;
    }
    .plate-type-box {
      position: absolute;
      z-index: 20;
      top: 0;
      left: 0;
      right: 0;
      bottom: 0;
      background: rgba(0, 0, 0, .5);
      ul {
        width: 3.55rem;
        height: 1.82rem;
        position: absolute;
        top:50%;
        left: 50%;
        transform: translate(-50%,-50%);
        -webkit-transform: translate(-50%,-50%);
        background: #fff;
        border-radius: .06rem;
        .item0 {
          border-radius: .06rem .06rem 0 0;
        }
        .item1 {
          border-bottom: .01rem solid #eee;
          border-top: .01rem solid #eee;
        }
        .item2 {
          border-radius: 0 0 .06rem .06rem;
        }
        li {
          height: .6rem;
          line-height: .6rem;
          font-size: .16rem;
          letter-spacing: .04rem;
          &.active {
            background: #3296FA;
            color: #fff;
          }
        }
      }
    }
    .keyboard-list {
      width: 100%;
      height: 1.95rem;
      background: #EAF7FD;
      padding: .05rem .04rem 0 .1rem;
      box-sizing: border-box;
      li {
        height: .39rem;
        line-height: .39rem;
        text-align: center;
        float: left;
        background: #fff;
        margin-top: .06rem;
        margin-right: .06rem;
        border-radius: .02rem;
        color: #222;
        font-size: .16rem;
      }
      li.step1 {
        width: .39rem;
      }
      li.step2,
      li.step3 {
        margin-right: .04rem;
        width: .32rem;
      }
      .delete {
        position: relative;
        &::before {
          content: '';
          display: block;
          position: absolute;
          width: .21rem;
          height: .21rem;
          background: url("../assets/billQuery/icon_delete.png") no-repeat;
          background-size: 100% 100%;
          top: .09rem;
          left: .06rem;
        }
      }
    }
    label {
      position: absolute;
      top: .09rem;
      left: .08rem;
      width: 2.7rem;
      height: .45rem;
      background: #fff;
      li {
        float: left;
        background: #f9f9f9;
        color: #222;
        width: 32rem;
        height: .45rem;
        &:not(:first-child) {
          margin-right: .02rem;
        }
      }
    }
  }
</style>
