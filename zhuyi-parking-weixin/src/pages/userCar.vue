<template>
  <div class="user-cars-box">
    <div class="banner"></div>
    <div class="single-car" v-for="item in cars" :key="item.id">
      <div class="car-brand">{{item.carBrand}}</div>
      <div class="car-unbind" @click="unbind(item.id)">解绑</div>
      <div class="plate-number">{{item.plateNumber}}</div>
      <div class="car-status" :class="'status' + item.status">{{item.status|statusFilter}}</div>
    </div>
    <div class="cover" v-if="cover">
      <div class="container">
        <div class="alert-title">提示</div>
        <div class="alert-info">
          您确认要解绑本车吗？解绑之后如需绑定车辆，需要在APP中操作。
        </div>
        <div class="btn cancel" @click="cover = false">取消</div>
        <div class="btn confirm" @click="goToUnbindApi">确认解绑</div>
      </div>
    </div>
    <div class="unbind-result" v-if="result">
      <div class="img"></div>
      <div class="info">解绑成功</div>
      <div class="btn" @click="turnBack">返 回</div>
    </div>
  </div>
</template>
<script>
import axios from '@/utils/request'
import msg from '@/utils/notification'
export default {
  data () {
    return {
      title: '我的车辆',
      cover: false, // 遮罩
      result: false, // 解绑结果遮罩
      unbindCarId: 0, // 将解绑的编号
      cars: []
    }
  },
  created () {
    document.title = this.title
    this.init()
  },
  methods: {
    init () {
      axios({url: '/platform/mycar/cars', method: 'post'}).then(res => {
        if (res.data.code === 1) {
          this.cars = res.data.items
          let fullInfo = this.$store.getters.userInfo
          Object.assign(fullInfo, {carCount: this.cars.length})
          this.$store.dispatch('saveUserInfo', fullInfo)
          if (this.cars.length === 0) {
            /** 个人中心 路由 */
            this.$router.replace({name: 'home'})
          }
        } else {
          msg('', '', res.data.message)
        }
      })
    },
    unbind (carId) {
      this.cover = true
      this.unbindCarId = carId
    },
    goToUnbindApi () {
      this.cover = false
      let tpl = {id: this.unbindCarId}
      axios({url: '/platform/mycar/carUnbind', method: 'post', params: tpl}).then(res => {
        if (res.data.code === 1) {
          this.result = true
          this.init()
        } else {
          msg('', '', res.data.message)
          this.init()
        }
      })
    },
    turnBack () {
      if (this.cars.length === 0) {
        /** 个人中心 路由 */
        this.$router.replace({name: 'home'})
      } else {
        this.result = false
      }
    }
  },
  filters: {
    statusFilter (status) {
      const statusMap = {
        0: '未认证',
        1: '认证中',
        2: '已认证',
        3: '认证失败'
      }
      return statusMap[status]
    }
  }
}
</script>
<style scoped lang="less">
.banner {
  width: 100%;
  height: 1.5rem;
  background: url("../assets/userCar/icon_CarBanner.png") no-repeat;
  background-size: 100% 100%;
}
.single-car {
  width: 3.55rem;
  height: 1.2rem;
  margin: .1rem .1rem 0 .1rem;
  background: url("../assets/userCar/img_binding.png") no-repeat;
  background-size: 100% 100%;
  position: relative;
  .car-brand {
    width: 2rem;
    height: .16rem;
    line-height: .16rem;
    font-size: .16rem;
    color: #fff;
    opacity: .4;
    position: absolute;
    top: .2rem;
    left: .12rem;
    text-align: left;
  }
  .car-unbind {
    width: .4rem;
    height: .12rem;
    position: absolute;
    top: .22rem;
    left: 3.05rem;
    font-size: .12rem;
    line-height: .12rem;
    text-align: left;
    color: #fff;
    opacity: .6;
    &::after {
      content: '';
      display: block;
      position: absolute;
      top: 0;
      left: .28rem;
      width: .12rem;
      height: .12rem;
      background: url("../assets/userCar/icon_unbind.png") no-repeat;
      background-size: 100% 100%;
    }
  }
  .plate-number {
    font-size: .18rem;
    height: .18rem;
    line-height: .18rem;
    width: 1.3rem;
    position: absolute;
    color: #fff;
    top: .62rem;
    left: .1rem;
    text-align: left;
    overflow: hidden;
    text-overflow: ellipsis;
    white-space: nowrap;
  }
  .car-status {
    width: .75rem;
    height: .3rem;
    line-height: .3rem;
    border-radius: .15rem;
    position: absolute;
    top: .56rem;
    left: 1.4rem;
    font-size: .14rem;
    &.status0 {
      background: #fff;
      color: #FA6464;
    }
    &.status1 {
      background: #fff;
      color: #222;
    }
    &.status2 {
      background: #fff;
      color: #46C81E;
    }
    &.status3 {
      background: #FA6464;
      color: #fff;
    }
  }
}
.cover {
  background: rgba(0, 0, 0, .5);
  position: absolute;
  top: 0;
  left: 0;
  bottom: 0;
  right: 0;
  z-index: 10;
  .container {
    width: 3rem;
    position: absolute;
    top:50%;
    left: 50%;
    transform: translate(-50%,-50%);
    -webkit-transform: translate(-50%,-50%);
    background: #fff;
    border-radius: .05rem;
  }
  .alert-title {
    position: absolute;
    top: .15rem;
    left: .15rem;
    width: .28rem;
    height: .2rem;
    line-height: .2rem;
    font-size: .14rem;
    color: #B4B4B4;
  }
  .alert-info {
    width: 2.74rem;
    line-height: .25rem;
    font-size: .16rem;
    color: #222;
    text-align: left;
    margin: .55rem .13rem;
  }
  .btn {
    position: absolute;
    bottom: .2rem;
    height: .2rem;
    line-height: .2rem;
  }
  .confirm {
    right: .16rem;
    color: #3296FA;
  }
  .cancel {
    right: 1.08rem;
    color: #B4B4B4;
  }
}
.unbind-result {
  background: #fff;
  position: absolute;
  top: 0;
  left: 0;
  bottom: 0;
  right: 0;
  z-index: 10;
  div {
    position: absolute;
  }
  .img {
    background: url("../assets/userCar/icon_succeed.png") no-repeat;
    background-size: 100% 100%;
    width: .8rem;
    height: .8rem;
    top: .5rem;
    left: 1.48rem;
  }
  .info {
    height: .24rem;
    line-height: .24rem;
    width: 100%;
    top: 1.5rem;
    font-size: .18rem;
    color: #222;
  }
  .btn {
    width: 3.1rem;
    height: .5rem;
    line-height: .5rem;
    color: #fff;
    top: 2.24rem;
    left: .33rem;
    background: #3296FA;
    border-radius: .25rem;
    font-size: .16rem;
  }
}
</style>
