<template>
  <div class="bg-content">
    <div class="home-title">弹出层</div>
    <div class="page-content">
      <div class="content-box">
        <el-button type="text" @click="dialogVisible = true">点击打开 Dialog</el-button>
        <el-dialog
          title="提示"
          :visible.sync="dialogVisible"
          width="30%"
          :before-close="handleClose">
          <span>这是一段信息</span>
          <span slot="footer" class="dialog-footer">
            <el-button @click="dialogVisible = false">取 消</el-button>
            <el-button type="primary" @click="dialogVisible = false">确 定</el-button>
          </span>
        </el-dialog>

        <br>
        <br>
        <br>
        <br>
        <br>

        <el-button type="text" @click="picVisible = true">打开图片</el-button>
        <el-dialog :visible.sync="picVisible" width="30%">
          <div class="pic-box"><img src="../../../static/img/head.jpg"alt=""></div>
        </el-dialog>

        <br>
        <br>
        <br>
        <br>
        <br>

        <el-button type="text" @click="dialogTableVisible = true">打开嵌套表格的 Dialog</el-button>
        <el-dialog title="收货地址" :visible.sync="dialogTableVisible">
          <el-table :data="gridData">
            <el-table-column property="date" label="日期" width="150"></el-table-column>
            <el-table-column property="name" label="姓名" width="200"></el-table-column>
            <el-table-column property="address" label="地址"></el-table-column>
          </el-table>
        </el-dialog>


        <br>
        <br>
        <br>
        <br>
        <br>

        <el-tooltip content="Top center" placement="top">
          <el-button>提示信息</el-button>
        </el-tooltip>

      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'layer',
  components: {
  },
  data () {
    return {
      dialogVisible: false,
      picVisible: false,
      dialogTableVisible: false,
      gridData: [{
          date: '2016-05-02',
          name: '王小虎',
          address: '上海市普陀区金沙江路 1518 弄'
        }, {
          date: '2016-05-04',
          name: '王小虎',
          address: '上海市普陀区金沙江路 1518 弄'
        }, {
          date: '2016-05-01',
          name: '王小虎',
          address: '上海市普陀区金沙江路 1518 弄'
        }, {
          date: '2016-05-03',
          name: '王小虎',
          address: '上海市普陀区金沙江路 1518 弄'
        }]
    };
  },
  methods: {
    handleClose(done) {
      this.$confirm('确认关闭？')
        .then(_ => {
          done();
        })
        .catch(_ => {});
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
  .content-box{
    background-color: #fff;
    width: 100%;
    padding: 24px;
    border: 1px solid #ebebeb;
    border-radius: 3px;
  }
  .pic-box{
    margin: 0 auto;
    width: 200px;
    height: 200px;
  }
  .pic-box img{
    width: 100%;
  }
</style>