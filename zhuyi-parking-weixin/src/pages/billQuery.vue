<template>
  <div class="bill-query-box">
    <transition name="fade">
      <loadingImg v-if="loading"></loadingImg>
    </transition>
    <div class="banner"></div>
    <div class="plate-number-box fx">
      <div class="plate-area" @click="keyboard = true; keyboardStep = 1">{{plateArea}}</div>
      <div class="plate-number">
        <input type="text" placeholder="请输入车牌号" v-model.trim="plateNumber" maxlength="6" @focus="blur">
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
    <div class="keyboard" v-if="keyboard">
      <div class="cancel" @click="cancel">关闭</div>
      <ul class="keyboard-list">
        <template v-if="keyboardStep === 1">
          <li class="step1"
              v-for="(item) in plateAreaList" :key="item.id"
              @click="getFirstLetter(item.id, item.name)"
          >
            {{item.name}}
          </li>
        </template>
        <template v-else-if="keyboardStep === 2">
          <li class="step2"
              v-for="(item) in plateAreaLetterList" :key="item.id"
              @click="getFullPrefix(item.name)"
          >
            {{item.name}}
          </li>
        </template>
        <template v-else-if="keyboardStep === 3">
          <li class="step3"
              v-for="(item, idx) in plateNumberList" :key="idx"
              @click="editPlateNumber(item)"
          >
            {{item}}
          </li>
          <li class="delete step3" @click="slicePlateNumber"></li>
        </template>
      </ul>
    </div>
  </div>
</template>
<script>
import axios from '@/utils/request'
import {getPrefixList, getPlateColor, login, getPrefixLetter} from '@/utils/api'
import msg from '@/utils/notification'
import loadingImg from '@/components/loading'
export default {
  components: {loadingImg},
  data () {
    return {
      loading: true,
      title: '账单查询',
      plateArea: '浙A',
      cacheFont: '', // 缓存的车牌前缀-省
      plateAreaList: [], // 省份简称
      plateAreaLetterList: [], // 省份字母
      plateType: '1', // 默认车牌颜色
      plateTypeList: [], // 车牌种类
      plateTypeBox: false,
      plateNumber: '',
      plateNumberList: [], // 固定车牌号键盘
      keyboardStep: 1,
      keyboard: false
    }
  },
  created () {
    document.title = this.title
    if (this.$store.getters.userInfo.accessToken) {
      this.loading = false
    }
    this.getCode()
    this.plateNumberList = [
      '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
      'Q', 'W', 'E', 'R', 'T', 'Y', 'U', 'I', 'O', 'P',
      'A', 'S', 'D', 'F', 'G', 'H', 'J', 'K', 'L', '学',
      'Z', 'X', 'C', 'V', 'B', 'N', 'M', '港', '澳'
    ]
  },
  methods: {
    getProvincesList () {
      getPrefixList().then(res => {
        if (res.data.code === 1) {
          this.plateAreaList = res.data.items
        } else {
          msg('', '', res.data.message)
        }
      })
    },
    getFirstLetter (id, name) {
      let tpl = {parentId: id}
      this.cacheFont = name
      getPrefixLetter(tpl).then(res => {
        if (res.data.code === 1) {
          this.keyboardStep = 2
          this.plateAreaLetterList = res.data.items
        } else {
          msg('', '', res.data.message)
        }
      })
    },
    getFullPrefix (letter) {
      this.plateArea = this.cacheFont + letter
      this.cacheFont = ''
      this.keyboardStep = 3
    },
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
        axios({url: '/platform/security/getWechatInfoJsapi', method: 'post', params: tpl1}).then(res => {
          if (res.data.code === 1) {
            let data = res.data.data
            this.logo = data.headimgurl
            this.name = data.nickName
            let tpl2 = {account: data.unionid, accountType: '2'}
            this.$store.dispatch('saveUserInfo', data)
            // code获取成功，判断用户是否再app绑定微信
            this.checkWechatIsRegister(tpl2)
          } else if (res.data.code === 10805) {
            if (this.$store.getters.userInfo.accessToken) {
              // 用户已经登入并从下个页面返回本页
              this.getPlateTypeList()
              this.getProvincesList()
            } else {
              msg('', '', '请在公众号："逐一科技"中操作!')
            }
          } else {
            /** 404 路由 */
            this.$router.replace({name: 'notFound'})
          }
        })
      } else if (!this.$store.getters.userInfo.accessToken) {
        msg('', '', '请在公众号："逐一科技"中操作!')
      }
    },
    checkWechatIsRegister (json) {
      axios({url: '/platform/security/accountexist', method: 'post', params: json}).then(res => {
        if (res.data.code === 1) {
          let data = res.data.data
          if (data.registered) {
            // 注册过,自动登陆
            let loginTpl = {account: this.$store.getters.userInfo.unionid, terminateType: 3, loginType: 3}
            login(loginTpl).then(res => {
              if (res.data.code === 1) {
                this.loading = false
                let fullInfo = this.$store.getters.userInfo
                Object.assign(fullInfo, res.data.data)
                this.$store.dispatch('saveUserInfo', fullInfo)
                this.getPlateTypeList()
                this.getProvincesList()
              } else {
                msg('', '', res.data.message)
              }
            })
          } else {
            // 没有注册,跳转页面
            /** 用户注册 路由 */
            this.$router.replace({name: 'userPhone', query: {returnURL: 'billQuery'}})
          }
        } else {
          msg('', '', res.data.message)
        }
      })
    },
    blur (e) {
      e.target.blur()
      this.keyboard = true
      this.keyboardStep = 3
    },
    cancel () {
      this.keyboard = false
      this.keyboardStep = 1
    },
    toPitchType () {
      this.plateTypeBox = true
    },
    selectPlateType (n) {
      this.plateType = n
      this.plateTypeBox = false
      if (n !== '6' && this.plateNumber.length > 5) {
        this.plateNumber = this.plateNumber.slice(0, 5)
      }
    },
    editPlateNumber (str) {
      let length
      if (this.plateType === '6') {
        length = 6
      } else {
        length = 5
      }
      if (this.plateNumber.length < length) {
        this.plateNumber += str
      }
    },
    slicePlateNumber () {
      this.plateNumber = this.plateNumber.slice(0, -1)
    },
    getBillList () {
      if (this.plateNumber.length >= 5) {
        this.$store.dispatch('QueryPlateNumber', this.plateArea + this.plateNumber)
        sessionStorage.setItem('queryPlateColor', this.plateType)
        /** 账单查询列表 路由 */
        this.$router.push({name: 'billQueryList'})
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
.bill-query-box {
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
    width: 3.4rem;
    height: .63rem;
    position: absolute;
    top: 1.8rem;
    left: .18rem;
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
    width: 1.6rem;
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
      width: 1.4rem;
      font-size: .16rem;
    }
  }
  .plate-type {
    width: 1rem;
    height: 100%;
    line-height: .63rem;
    float: left;
    letter-spacing: .04rem;
    &::before {
      content: '';
      display: block;
      width: .1rem;
      height: .1rem;
      position: absolute;
      top: .27rem;
      left: 3.22rem;
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
}
</style>
