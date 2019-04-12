<template>
  <div class="bg-content">
    <div class="home-title">订单列表</div>
    <div class="page-content">
      <div class="search-box">
        <el-row :gutter="10">
          <el-col :span="6">
            <div class="input-item">
                <span>订单编号：</span>
                <div>
                  <el-input v-model="orderNum" placeholder="请输入内容"></el-input>
                </div>
            </div>
          </el-col>
          <el-col :span="6">
            <div class="input-item">
                <span>订单编号：</span>
                <div>
                  <el-input v-model="orderNum" placeholder="请输入内容"></el-input>
                </div>
            </div>
          </el-col>
          <el-col :span="6">
            <div class="input-item">
                <span>订单编号：</span>
                <div>
                  <el-input v-model="orderNum" placeholder="请输入内容"></el-input>
                </div>
            </div>
          </el-col>
        </el-row>
        <el-row :gutter="10">
          <el-col :span="24">
            <el-button type="primary" icon="el-icon-search" size="small">搜索</el-button>
          </el-col>
        </el-row>
      </div>
      <div class="table-box">
        <el-table
          :data="tableData"
          style="width: 100%">
          <el-table-column
            label="日期"
            width="180">
            <template slot-scope="scope">
              <i class="el-icon-time"></i>
              <span style="margin-left: 10px">{{ scope.row.date }}</span>
            </template>
          </el-table-column>
          <el-table-column
            label="姓名"
            width="120">
            <template slot-scope="scope">
                <span>{{ scope.row.name }}</span>
            </template>
          </el-table-column>
          <el-table-column
            label="地址"
            width="300">
            <template slot-scope="scope">
                <span>住址: {{ scope.row.address }}</span>
            </template>
          </el-table-column>
          <el-table-column label="操作">
            <template slot-scope="scope">
              <el-button
                size="mini"
                @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
              <el-button
                size="mini"
                type="danger"
                @click="handleDelete(scope.$index, scope.row)">删除</el-button>
            </template>
          </el-table-column>
        </el-table>
        <div class="pagination-box">
          <el-pagination
            layout="total, sizes, prev, pager, next, jumper"
            :total="20">
          </el-pagination>
        </div>
      </div>
      <el-dialog 
        title="修改收货地址"
        :visible.sync="dialogVisible"
        width="30%">
        <el-form>
          <el-form-item label="收货地址" :label-width="formLabelWidth">
            <el-input auto-complete="off"></el-input>
          </el-form-item>
        </el-form>
        <div slot="footer">
          <el-button @click="dialogVisible = false">取 消</el-button>
          <el-button type="primary" @click="dialogVisible = false">确 定</el-button>
        </div>
      </el-dialog>
    </div>
  </div>
</template>

<script>
export default {
  name: 'basetable',
  components: {
  },
  data () {
    return {
      orderNum: '',
      tableData: [{
        date: '2016-05-02',
        name: '王小虎',
        address: '上海市普陀区金沙江路 1518 弄'
      }, {
        date: '2016-05-04',
        name: '王小虎',
        address: '上海市普陀区金沙江路 1517 弄'
      }, {
        date: '2016-05-01',
        name: '王小虎',
        address: '上海市普陀区金沙江路 1519 弄'
      }, {
        date: '2016-05-03',
        name: '王小虎',
        address: '上海市普陀区金沙江路 1516 弄'
      }],
      dialogVisible: false,
      formLabelWidth: '100px'
    }
  },
  methods: {
    handleEdit(index, row) {
      this.dialogVisible = true
      //console.log(index, row)
    },
    handleDelete(index, row) {
      this.tableData.splice(index,1);
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
  .search-box{
    background-color: #fff;
    width: 100%;
    padding: 24px;
    border: 1px solid #ebebeb;
    border-radius: 3px;
  }
  .table-box{
    margin: 20px 0;
    padding: 24px;
    border: 1px solid #ebebeb;
    border-radius: 3px;
    background-color: #fff;
  }
  .input-item{
    display: flex;
    align-items: center;
  }
  .input-item > span {
    min-width: 75px;
  }
  .el-row {
    margin-bottom: 20px;
  }
  .el-row:last-child {
    margin-bottom: 0;
  }
  .pagination-box{
    display: flex;
    justify-content: flex-end;
    padding-top: 20px;
  }
</style>