<template>
  <div class="app-container">
    <div class="filter-container">
      <el-select @change='handleFilter' class="filter-item" style="width: 200px" v-model="listQuery.type"
                placeholder="分类">
        <el-option v-for="item in typeOptions" :key="item.type" :value="item.type" :label="item.typeDes">
        </el-option>
      </el-select>
      <el-input @keyup.enter.native="handleFilter" style="width: 200px;" class="filter-item" placeholder="姓名"
                v-model="listQuery.name">
      </el-input>
      <el-input @keyup.enter.native="handleFilter" style="width: 200px;" class="filter-item" placeholder="电话"
                v-model="listQuery.telephone">
      </el-input>
      <el-input @keyup.enter.native="handleFilter" style="width: 200px;" class="filter-item" placeholder="邮箱"
                v-model="listQuery.mail">
      </el-input>
      <el-input @keyup.enter.native="handleFilter" style="width: 200px;" class="filter-item" placeholder="公司名称"
                v-model="listQuery.company">
      </el-input>
      <div>
        <el-date-picker v-model="listQuery.startTime" type="date" placeholder="开始时间" value-format="yyyy-MM-dd">
        </el-date-picker>
        <el-date-picker v-model="listQuery.endTime" type="date" placeholder="结束时间" value-format="yyyy-MM-dd">
        </el-date-picker>
        <el-button class="filter-item" type="primary" icon="el-icon-search" @click="handleFilter">查询</el-button>
      </div>
    </div>
    <el-table :key='tableKey' :data="list" v-loading="listLoading" element-loading-text="加载中"
              element-loading-spinner="el-icon-loading"
              element-loading-background="rgba(0, 0, 0, 0.8)"
              border fit highlight-current-row style="width: 100%">
    <el-table-column type="index" width="60" align="center" label="序号">
    </el-table-column>
    <el-table-column width="120px" align="center" label="分类">
        <template slot-scope="scope">
        <span>{{scope.row.type|typeFilter}}</span>
        </template>
    </el-table-column>
    <el-table-column width="140px" align="center" label="姓名">
        <template slot-scope="scope">
        <span>{{scope.row.name}}</span>
        </template>
    </el-table-column>
    <el-table-column width="160px" align="center" label="电话">
        <template slot-scope="scope">
        <span>{{scope.row.telephone}}</span>
        </template>
    </el-table-column>
    <el-table-column width="220px" align="center" label="邮箱">
        <template slot-scope="scope">
        <span>{{scope.row.mail}}</span>
        </template>
    </el-table-column>
    <el-table-column width="300px" align="center" label="公司名称">
        <template slot-scope="scope">
        <span>{{scope.row.company}}</span>
        </template>
    </el-table-column>
    <el-table-column width="220px" align="center" label="创建时间">
      <template slot-scope="scope">
        <span>{{scope.row.creationTime}}</span>
      </template>
    </el-table-column>
    <el-table-column align="center" label="操作" class-name="small-padding">
      <template slot-scope="scope">
        <el-button type="primary" size="mini" @click="handleDetail(scope.row)">查看</el-button>
        <el-button type="primary" size="mini" @click="createDetail(scope.row)">备注</el-button>
      </template>
    </el-table-column>
    </el-table>
    <div v-show="!listLoading" class="pagination-container">
    <el-pagination background @size-change="handleSizeChange" @current-change="handleCurrentChange"
                   :current-page.sync="listQuery.pageNo"
                   :page-sizes="[10,20,30, 50]" :page-size="listQuery.pageSize"
                   layout="total, sizes, prev, pager, next, jumper" :total="totalCount">
    </el-pagination>
    </div>
    <!-- 查看详情 -->
    <el-dialog title="查看详情" :visible.sync="detailDialogFormVisible" center>
      <p>&emsp;&emsp;分类&emsp;&emsp;&emsp;{{tempDetail.type|typeFilter}}</p>
      <p>&emsp;&emsp;姓名&emsp;&emsp;&emsp;{{tempDetail.name}}</p>
      <p>&emsp;&emsp;电话&emsp;&emsp;&emsp;{{tempDetail.telephone}}</p>
      <p>&emsp;&emsp;邮箱&emsp;&emsp;&emsp;{{tempDetail.mail}}</p>
      <p>公司名称&emsp;&emsp;&emsp;{{tempDetail.company}}</p>
      <p>&emsp;&emsp;备注&emsp;&emsp;&emsp;{{tempDetail.content}}</p>
      <div slot="footer" class="dialog-footer">
        <el-button @click="detailDialogFormVisible = false">确 定</el-button>
      </div>
    </el-dialog>
    <!-- 添加备注 -->
    <el-dialog title="备注" :visible.sync="remarkDialogFormVisible" center width="600px">
      <el-form :rules="rules" ref="dataForm" :model="remark" label-position="left" label-width="100px">
        <el-form-item label="添加备注" prop="content">
          <el-input type="textarea" :rows="4" style="width:300px;" placeholder="(不得超过120个字)" :maxlength="120"
                    v-model="remark.content"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="createData">保 存</el-button>
        <el-button @click="remarkDialogFormVisible = false">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { fetchBusiList, fetchTypeList, getBusi, remarkBusi } from '@/api/cooperation'

export default {
  name: 'Cooperation',
  // directives: {waves},
  data() {
    return {
      tableKey: 0,
      list: null,
      totalCount: null,
      listLoading: true,
      listQuery: {
        pageNo: 1,
        pageSize: 10,
        name: undefined,
        type: undefined,
        telephone: undefined,
        mail: undefined,
        company: undefined,
        startTime: undefined,
        endTime: undefined
      },
      tempDetail: {
        type: undefined,
        name: undefined,
        telephone: undefined,
        mail: undefined,
        company: undefined,
        content: undefined
      },
      remark: {
        id: null,
        content: ''
      },
      detailDialogFormVisible: false,
      remarkDialogFormVisible: false,
      typeOptions: [],
      rules: {}
    }
  },
  filters: {
    typeFilter(type) {
      const typeMap = {
        1: '商业合作',
        2: '广告投放'
      }
      return typeMap[type]
    }
  },
  created() {
    this.getTypeOptions()
    this.getList()
  },
  methods: {
    getList() {
      fetchBusiList(this.listQuery).then(response => {
        if (response.data.code === 1) {
          this.list = response.data.items
          this.totalCount = response.data.totalCount
          this.listLoading = false
        }
      })
    },
    getTypeOptions() {
      fetchTypeList().then(response => {
        if (response.data.code === 1) {
          this.typeOptions = response.data.items
        }
      })
    },
    handleFilter() {
      this.listQuery.pageNo = 1
      this.getList()
    },
    handleSizeChange(val) {
      this.listQuery.pageSize = val
      this.getList()
    },
    handleCurrentChange(val) {
      this.listQuery.pageNo = val
      this.getList()
    },
    handleDetail(row) {
      const tempData = { id: row.id }
      getBusi(tempData).then(response => {
        if (response.data.code === 1) {
          this.tempDetail = Object.assign({}, response.data.data)
          this.detailDialogFormVisible = true
        }
      })
    },
    createDetail(row) {
      const tempData = { id: row.id }
      getBusi(tempData).then(response => {
        if (response.data.code === 1) {
          this.remark.content = response.data.data.content || ''
        } else {
          this.remark.content = ''
        }
      })
      this.remarkDialogFormVisible = true
      this.remark.id = row.id
      this.$nextTick(() => {
        this.$refs['dataForm'].clearValidate()
      })
    },
    createData() {
      this.$refs['dataForm'].validate((valid) => {
        if (valid) {
          // const tempData = { id: this.id, content: this.content }
          remarkBusi(this.remark).then(response => {
            if (response.data.code === 1) {
              this.remarkDialogFormVisible = false
              this.$notify({
                title: '成功',
                message: '添加备注成功',
                type: 'success',
                duration: 2000
              })
              this.getList()
            }
          })
        }
      })
    }
  }
}
</script>

<style scoped>

</style>
