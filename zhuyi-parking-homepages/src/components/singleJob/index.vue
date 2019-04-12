<template>
  <div class="job-box">
    <div class="job-header">
      <div class="position">
        {{item.position}}&nbsp;&nbsp;
        <div class="department">
          {{department}}
        </div>
      </div>
    </div>
    <div class="content">
      <div class="tap">
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;任职要求
      </div>
      <div class="text" v-html="item.content">
        <!--{{item.content}}-->
      </div>
    </div>
  </div>
</template>
<script>
export default {
  name: 'job',
  props: ['item'],
  data() {
    return {
      department: ''
    }
  },
  created() {
    switch (this.item.department) {
      case 1:
        this.department = '研发部'
        break
      case 2:
        this.department = '运营部'
        break
      case 3:
        this.department = '行政部'
        break
      case 4:
        this.department = '财务部'
        break
      case 5:
        this.department = '营销部'
        break
      default:
        this.department = '二次元'
    }
  }
}
</script>
<style lang="less" scoped>
  .job-box {
    width: 580px;
    height: 420px;
    position: relative;
    box-shadow:8px 8px 18px rgba(0, 0, 0, .05);
    /*margin-top: 20px;*/
    .job-header {
      width: 100%;
      height: 60px;
      background: #3296FA;
      line-height: 60px;
      border-radius: 8px 8px 0 0;
      font-size: 24px;
      .position {
        display: inline-block;
        width: 100%;
        height: 100%;
        line-height: 60px;
      }
      .department {
        display: inline-block;
        height: 60px;
        line-height: 60px;
        font-size: 18px;
      }
    }
    .content {
      width: 100%;
      height: 360px;
      background: #fff;
      .tap {
        width: 100%;
        height: 50px;
        line-height: 50px;
        color: #222;
        font-size: 16px;
        text-align: left;
      }
      .job-duty {
        width: 70px;
        height: 24px;
        background: #3296FA;
        color: #fff;
        font-size: 14px;
        line-height: 24px;
        border-radius: 12px;
        top: 47px;
        left: 34px;
      }
      .text {
        width: 100%;
        background: #fff;
        box-sizing: border-box;
        padding: 0 34px 30px 34px;
        height: 318px;
        color: #666;
        overflow-y: auto;
        text-align: left;
        font-size: 14px;
        line-height: 24px;
      }
      .text::-webkit-scrollbar {width:2px;}
      .text::-webkit-scrollbar-track{background-color:#fff;}
      .text::-webkit-scrollbar-thumb{background-color:rgba(50, 150, 250, .5);}
      .text::-webkit-scrollbar-thumb:hover {background-color:rgba(50, 150, 250, .5);}
      .text::-webkit-scrollbar-thumb:active {background-color:rgba(50, 150, 250, .5);}
    }
  }
</style>
