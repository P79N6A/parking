<template>
  <div class="app-container">
    <div class="filter-container">
      <el-input @keyup.enter.native="handleFilter" style="width: 200px;" class="filter-item" placeholder="职位名称"
                v-model="listQuery.position">
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
    <el-table-column width="180px" min-width="80px" align="center" label="职位名称">
      <template slot-scope="scope">
        <span>{{scope.row.position}}</span>
      </template>
    </el-table-column>
    <el-table-column width="140px" min-width="80px" align="center" label="所属部门">
      <template slot-scope="scope">
        <span>{{scope.row.department|typeFilter}}</span>
      </template>
    </el-table-column>
    <el-table-column width="120px" align="center" label="发布状态">
      <template slot-scope="scope">
        <span>{{scope.row.status|statusFilter}}</span>
      </template>
    </el-table-column>
    <el-table-column width="220px" align="center" label="上传时间">
      <template slot-scope="scope">
        <span>{{scope.row.creationTime}}</span>
      </template>
    </el-table-column>
    <el-table-column width="180px" align="center" label="推荐位置">
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
    <!-- 新增、编辑招聘信息对话框 -->
    <el-dialog :title="textMap[dialogStatus]" :visible.sync="dialogFormVisible" center>
      <el-form :rules="rules" ref="dataForm" :model="tempEmploy" label-position="left" label-width="100px">
        <el-form-item label="职位名称" prop="position">
          <el-input v-model="tempEmploy.position"></el-input>
        </el-form-item>
        <el-form-item label="所属部门" prop="department">
          <el-select class="filter-item" v-model="tempEmploy.department" clearable placeholder="请选择">
            <el-option v-for="item in  departmentOptions" :key="item.departmentCode" :label="item.departmentDes"
                       :value="item.departmentCode">
            </el-option>
          </el-select>
        </el-form-item>
        <!-- <el-form-item label="招聘图片" prop="imageUrl">
          <el-upload
            :action="upload()"
            :on-success="handleSmallAvatarSuccess"
            :with-credentials="true"
            :show-file-list="false">
            <el-button size="small" type="primary">点击上传</el-button>
            <img v-if="tempEmploy.imageUrl" :src="tempEmploy.imageUrl" class="avatar">
          </el-upload>
        </el-form-item> -->
        <el-form-item label="内容编辑" prop="content">
          <quill-editor v-model="tempEmploy.content"
                        @change="onEditorChange($event)">
          </quill-editor>
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
  fetchEmployList, createEmploy, getEmploy, getDepartment,
  updateEmploy, deleteEmploy, stickEmploy, soldOutEmploy
} from '@/api/recruitment'
import { getStatusList } from '@/api/common'
import 'quill/dist/quill.core.css'
import 'quill/dist/quill.snow.css'
import 'quill/dist/quill.bubble.css'
import { quillEditor } from 'vue-quill-editor'

export default {
  name: 'Recruitment',
  components: { quillEditor },
  data() {
    return {
      tableKey: 0,
      list: null,
      totalCount: null,
      listLoading: true,
      listQuery: {
        pageNo: 1,
        pageSize: 10,
        position: undefined,
        status: undefined,
        startTime: undefined,
        endTime: undefined
      },
      tempEmploy: {
        department: undefined,
        position: undefined,
        // imageUrl: undefined,
        content: '',
        status: null
      },
      // img: '',
      dialogFormVisible: false,
      dialogStatus: '',
      textMap: {
        update: '编辑',
        create: '创建'
      },
      departmentOptions: [],
      statusOptions: [],
      rules: {
        department: [{ required: true, message: '请选择所属部门', trigger: 'change' }],
        position: [{ required: true, message: '请输入职位名称', trigger: 'blur' }],
        // imageUrl: [{ required: true, message: '请上传招聘图片', trigger: 'blur' }],
        content: [{ required: true, message: '请输入招聘内容', trigger: 'blur' }]
      }
    }
  },
  filters: {
    typeFilter(type) {
      const typeMap = {
        1: '研发部',
        2: '运营部',
        3: '行政部',
        4: '财务部',
        5: '营销部'
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
    this.getList()
  },
  methods: {
    onEditorChange({ editor, html, text }) {
      this.tempEmploy.content = html
    },
    getList() {
      fetchEmployList(this.listQuery).then(response => {
        if (response.data.code === 1) {
          this.list = response.data.items
          this.totalCount = response.data.totalCount
          this.listLoading = false
        }
      })
    },
    getDepartmentOptions() {
      getDepartment().then(response => {
        if (response.data.code === 1) {
          this.departmentOptions = response.data.items
        }
      })
    },
    getStatusOptions() {
      getStatusList().then(response => {
        if (response.data.code === 1) {
          this.statusOptions = response.data.items
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
      this.tempEmploy = {
        id: undefined,
        department: undefined,
        position: undefined,
        // imageUrl: undefined,
        content: '',
        status: null
      }
    },
    handleCreate() {
      this.resetTemp()
      this.getDepartmentOptions()
      this.dialogStatus = 'create'
      this.dialogFormVisible = true
      this.$nextTick(() => {
        this.$refs['dataForm'].clearValidate()
      })
    },
    createData(status) {
      this.$refs['dataForm'].validate((valid) => {
        if (valid) {
          this.tempEmploy.status = status
          // this.tempEmploy.imageUrl = this.img
          createEmploy(this.tempEmploy).then(response => {
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
      this.getDepartmentOptions()
      const tempData = { id: row.id }
      getEmploy(tempData).then(response => {
        if (response.data.code === 1) {
          this.tempEmploy = Object.assign({}, response.data.data)
          // this.img = response.data.data.imageUrl
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
          const tempData = Object.assign({}, this.tempEmploy)
          tempData.status = status
          // tempData.imageUrl = this.img
          updateEmploy(tempData).then(response => {
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
      deleteEmploy(tempData).then((response) => {
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
      stickEmploy(tempData).then((response) => {
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
      soldOutEmploy(tempData).then((response) => {
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
    }
    // handleSmallAvatarSuccess(res, file) {
    //   this.tempEmploy.imageUrl = URL.createObjectURL(file.raw)
    //   this.img = res.data.fileUrl
    // },
    // upload() {
    //   return this.baseURL + '/portal/public/fileUploading'
    // }
  }
}
</script>

<style scoped>
  /* .avatar {
    width: 200px;
    height: 200px;
    display: block;
  } */
</style>
