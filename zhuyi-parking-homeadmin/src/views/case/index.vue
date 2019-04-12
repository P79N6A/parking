<template>
  <div class="app-container">
    <div class="filter-container">
      <el-select @change='handleFilter' class="filter-item" style="width: 200px" v-model="listQuery.type"
                placeholder="车场分类">
        <el-option v-for="item in  typeOptions" :key="item.type" :label="item.typeDes" :value="item.type">
        </el-option>
      </el-select>
      <el-input @keyup.enter.native="handleFilter" style="width: 200px;" class="filter-item" placeholder="地址名称"
                v-model="listQuery.name">
      </el-input>
      <el-select @change='handleFilter' class="filter-item" style="width: 200px" v-model="listQuery.status"
                placeholder="发布状态">
        <el-option v-for="item in statusOptions" :key="item.status" :value="item.status" :label="item.statusDes">
        </el-option>
      </el-select>
      <el-date-picker v-model="listQuery.startTime" type="date" placeholder="开始时间" value-format="yyyy-MM-dd">
      </el-date-picker>
      <el-date-picker v-model="listQuery.endTime" type="date" placeholder="结束时间" value-format="yyyy-MM-dd">
      </el-date-picker>
      <el-button class="filter-item" type="primary" icon="el-icon-search" @click="handleFilter">查询</el-button>
      <el-button class="filter-item" type="primary" icon="el-icon-edit" @click="handleCreate">添加</el-button>
    </div>
    <el-table :key='tableKey' :data="list" v-loading="listLoading" element-loading-text="加载中"
              element-loading-spinner="el-icon-loading"
              element-loading-background="rgba(0, 0, 0, 0.8)"
              border fit highlight-current-row style="width: 100%">
    <el-table-column type="index" width="60" align="center" label="序号">
    </el-table-column>
    <el-table-column width="140px" align="center" label="车场分类">
      <template slot-scope="scope">
        <span>{{scope.row.type|typeFilter}}</span>
      </template>
    </el-table-column>
    <el-table-column width="320px" align="center" label="地址名称">
      <template slot-scope="scope">
        <span>{{scope.row.name}}</span>
      </template>
    </el-table-column>
    <el-table-column width="120px" align="center" label="发布状态">
      <template slot-scope="scope">
        <span>{{scope.row.status|statusFilter}}</span>
      </template>
    </el-table-column>
    <el-table-column width="180px" align="center" label="发布时间">
      <template slot-scope="scope">
        <span>{{scope.row.creationTime}}</span>
      </template>
    </el-table-column>
    <el-table-column width="120px" align="center" label="推荐位置">
      <template slot-scope="scope">
        <span class="link-type" @click="handleStick(scope.row)">{{scope.row.stick ? '取消置顶' : '置顶'}}</span>
      </template>
    </el-table-column>
    <el-table-column min-width="180px" align="center" label="操作" class-name="small-padding">
      <template slot-scope="scope">
        <el-button type="primary" size="mini" @click="handleUpdate(scope.row)">编辑</el-button>
        <el-button type="danger" size="mini" @click="handleDelete(scope.row)">删除</el-button>
        <el-button :type="scope.row.status==1?'danger':'primary'" @click='handleSoldOut(scope.row)' size="mini"
                    icon="edit">{{scope.row.status==1?'下架':'上架'}}
        </el-button>
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
    <!-- 新增、编辑车场案例对话框 -->
    <el-dialog :title="textMap[dialogStatus]" :visible.sync="dialogFormVisible" center>
      <el-form :rules="rules" ref="dataForm" :model="tempCase" label-position="left" label-width="100px">
        <el-form-item label="车场分类" prop="type">
          <el-select class="filter-item" v-model="tempCase.type" clearable placeholder="请选择">
            <el-option v-for="item in  typeOptions" :key="item.type" :label="item.typeDes"
                       :value="item.type">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="车场名称" prop="name">
          <el-input v-model="tempCase.name"></el-input>
        </el-form-item>
        <el-form-item label="车场图片" prop="imageUrl">
          <el-upload
            :action="upload()"
            :before-upload="beforeAvatarUpload"
            :on-success="handleAvatarSuccess"
            :with-credentials="true"
            :show-file-list="false">
            <el-button size="small" type="primary">点击上传</el-button>
            <div slot="tip" class="el-upload__tip">只能上传jpg/png文件，且不超过500kb，建议尺寸为380*460</div>
            <img v-if="tempCase.imageUrl" :src="tempCase.imageUrl" class="avatar">
          </el-upload>
        </el-form-item>
        <el-form-item label="描述信息" prop="content">
          <el-input type="textarea" :rows="4" style="width:300px;" placeholder="请输入停车场描述"
                    v-model="tempCase.content"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button v-if="dialogStatus=='create'" type="primary" @click="createData(0)">保 存</el-button>
        <el-button v-else type="primary" @click="updateData(0)">保 存</el-button>
        <el-button v-if="dialogStatus=='create'" type="primary" @click="createData(1)">发 布</el-button>
        <el-button v-else type="primary" @click="updateData(1)">发 布</el-button>
        <el-button @click="dialogFormVisible = false">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import {
  fetchCaseList, fetchTypeList, createCase,
  getCase, updateCase, deleteCase, stickCase, soldOutCase
} from '@/api/case'
import { getStatusList } from '@/api/common'

export default {
  name: 'Case',
  data() {
    return {
      tableKey: 0,
      list: null,
      totalCount: null,
      listLoading: true,
      listQuery: {
        pageNo: 1,
        pageSize: 10,
        type: undefined,
        name: undefined,
        status: undefined,
        startTime: undefined,
        endTime: undefined
      },
      tempCase: {
        type: undefined,
        name: undefined,
        imageUrl: undefined,
        status: null,
        content: undefined
      },
      img: '',
      dialogFormVisible: false,
      dialogStatus: '',
      textMap: {
        update: '编辑',
        create: '创建'
      },
      typeOptions: [],
      statusOptions: [],
      rules: {
        type: [{ required: true, message: '请选择车场类型', trigger: 'change' }],
        name: [{ required: true, message: '请输入车场名称', trigger: 'blur' }],
        imageUrl: [{ required: true, message: '请上传车场图片', trigger: 'blur' }],
        content: [{ required: true, message: '请输入车场描述', trigger: 'blur' }]
      }
    }
  },
  filters: {
    typeFilter(type) {
      const typeMap = {
        1: '商业综合体',
        2: '商业写字楼',
        3: '大型住宅区',
        4: '学校及医院',
        5: '旅游、酒店及单位'
      }
      return typeMap[type]
    },
    statusFilter(status) {
      const statusMap = {
        1: '已发布',
        0: '未发布'
      }
      return statusMap[status]
    }
  },
  created() {
    this.getStatusOptions()
    this.getTypeOptions()
    this.getList()
  },
  methods: {
    getStatusOptions() {
      getStatusList().then(response => {
        if (response.data.code === 1) {
          this.statusOptions = response.data.items
        }
      })
    },
    getList() {
      fetchCaseList(this.listQuery).then(response => {
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
    resetTemp() {
      this.tempCase = {
        id: undefined,
        type: undefined,
        name: undefined,
        imageUrl: undefined,
        status: null,
        content: undefined
      }
    },
    handleCreate() {
      this.resetTemp()
      this.dialogStatus = 'create'
      this.dialogFormVisible = true
      this.$nextTick(() => {
        this.$refs['dataForm'].clearValidate()
      })
    },
    createData(status) {
      this.$refs['dataForm'].validate((valid) => {
        if (valid) {
          this.tempCase.status = status
          this.tempCase.imageUrl = this.img
          createCase(this.tempCase).then(response => {
            if (response.data.code === 1) {
              this.dialogFormVisible = false
              this.$notify({
                title: '成功',
                message: '创建成功',
                type: 'success',
                duration: 2000
              })
              this.getList()
            }
          })
        }
      })
    },
    handleUpdate(row) {
      const tempData = { id: row.id }
      getCase(tempData).then(response => {
        if (response.data.code === 1) {
          this.tempCase = Object.assign({}, response.data.data)
          this.img = response.data.data.imageUrl
          this.dialogStatus = 'update'
          this.dialogFormVisible = true
          this.$nextTick(() => {
            this.$refs['dataForm'].clearValidate()
          })
        }
      })
    },
    updateData(status) {
      this.$refs['dataForm'].validate((valid) => {
        if (valid) {
          const tempData = Object.assign({}, this.tempCase)
          tempData.status = status
          tempData.imageUrl = this.img
          updateCase(tempData).then(response => {
            if (response.data.code === 1) {
              this.dialogFormVisible = false
              this.$notify({
                title: '成功',
                message: '更新成功',
                type: 'success',
                duration: 2000
              })
              this.getList()
            }
          })
        }
      })
    },
    handleDelete(row) {
      const tempData = { id: row.id }
      deleteCase(tempData).then((response) => {
        if (response.data.code === 1) {
          this.$notify({
            title: '成功',
            message: '删除成功',
            type: 'success',
            duration: 2000
          })
          this.getList()
        }
      })
    },
    handleStick(row) {
      const stick = !row.stick
      const tempData = { id: row.id, stick: stick }
      stickCase(tempData).then((response) => {
        if (response.data.code === 1) {
          this.$notify({
            title: '成功',
            message: stick ? '置顶成功' : '取消置顶成功',
            type: 'success',
            duration: 2000
          })
          this.getList()
        }
      })
    },
    handleSoldOut(row) {
      const status = parseInt(row.status) === 0 ? 1 : 0
      const tempData = { id: row.id, status: status }
      soldOutCase(tempData).then((response) => {
        if (response.data.code === 1) {
          this.$notify({
            title: '成功',
            message: parseInt(tempData.status) === 0 ? '下架成功' : '上架成功',
            type: 'success',
            duration: 2000
          })
          this.getList()
        }
      })
    },
    beforeAvatarUpload(file) {
      const isImg = file.type === 'image/jpeg' || 'image/png'
      const isLt500K = file.size / 1024 < 500

      if (!isImg) {
        this.$message.error('上传图片只能是jpg/png格式!')
      }
      if (!isLt500K) {
        this.$message.error('上传图片大小不能超过 500KB!')
      }
      return isImg && isLt500K
    },
    handleAvatarSuccess(res, file) {
      this.tempCase.imageUrl = URL.createObjectURL(file.raw)
      this.img = res.data.fileUrl
    },
    upload() {
      return process.env.BASE_API + '/portal/public/fileUploading'
    }
  }
}
</script>

<style scoped>
  .avatar {
    width: 380px;
    height: 460px;
    display: block;
  }
</style>
