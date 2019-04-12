<template>
  <div class="app-container">
    <div class="filter-container">
      <el-select @change='handleFilter' class="filter-item" style="width: 200px" v-model="listQuery.type"
                placeholder="新闻类型">
        <el-option v-for="item in typeOptions" :key="item.type" :value="item.type" :label="item.typeDes">
        </el-option>
      </el-select>
      <el-input @keyup.enter.native="handleFilter" style="width: 200px;" class="filter-item" placeholder="新闻标题"
                v-model="listQuery.title">
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
    <el-table-column width="120px" align="center" label="新闻类型">
        <template slot-scope="scope">
        <span>{{scope.row.type|typeFilter}}</span>
        </template>
    </el-table-column>
    <el-table-column width="320px" align="center" label="标题">
        <template slot-scope="scope">
        <span>{{scope.row.title}}</span>
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
    <el-table-column width="120px" align="center" label="推荐位置">
        <template slot-scope="scope">
        <span class="link-type" @click="handleStick(scope.row)">{{scope.row.stick ? '取消置顶' : '置顶'}}</span>
        </template>
    </el-table-column>
    <el-table-column min-width="180px" align="center" label="操作" class-name="small-padding">
        <template slot-scope="scope">
        <el-button type="primary" size="mini" @click="handleUpdate(scope.row)">编辑</el-button>
        <el-button type="danger" size="mini" @click="handleDelete(scope.row)">删除</el-button>
        <el-button :type="scope.row.status==0?'primary':'danger'" @click='handleSoldOut(scope.row)' size="mini"
                    icon="edit">{{scope.row.status==0?'上架':'下架'}}
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
    <!-- 新增、编辑新闻对话框 -->
    <el-dialog :title="textMap[dialogStatus]" :visible.sync="dialogFormVisible" center>
      <el-form :rules="rules" ref="dataForm" :model="tempNews" label-position="left" label-width="100px">
        <el-form-item label="新闻分类" prop="type">
          <el-select class="filter-item" v-model="tempNews.type" clearable placeholder="请选择">
            <el-option v-for="item in  typeOptions" :key="item.type" :label="item.typeDes"
                       :value="item.type">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="新闻标题" prop="title">
          <el-input v-model="tempNews.title"></el-input>
        </el-form-item>
        <el-form-item label="封面图片" prop="imageUrl">
          <el-upload
            :action="upload()"
            :before-upload="beforeAvatarUpload"
            :on-success="handleBigAvatarSuccess"
            :with-credentials="true"
            :show-file-list="false">
            <el-button size="small" type="primary">点击上传</el-button>
            <div slot="tip" class="el-upload__tip">只能上传jpg/png文件，且不超过500kb，建议尺寸为1200*400</div>
            <img v-if="tempNews.imageUrl" :src="tempNews.imageUrl" class="avatar">
          </el-upload>
        </el-form-item>
        <el-form-item label="新闻摘要" prop="summary">
          <el-input type="textarea" :rows="4" placeholder="请输入新闻摘要"
                    v-model="tempNews.summary"></el-input>
        </el-form-item>
        <el-form-item label="描述信息" prop="content">
          <quill-editor v-model="tempNews.content"
                        :options="editorOption"
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
  fetchNewsList, fetchTypeList, createNews,
  getNews, updateNews, deleteNews, stickNews, soldOutNews
} from '@/api/news'
import { getStatusList } from '@/api/common'
import 'quill/dist/quill.core.css'
import 'quill/dist/quill.snow.css'
import 'quill/dist/quill.bubble.css'
import { quillEditor } from 'vue-quill-editor'

export default {
  name: 'News',
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
        type: undefined,
        title: undefined,
        status: undefined,
        startTime: undefined,
        endTime: undefined
      },
      imgBig: '',
      tempNews: {
        type: undefined,
        title: undefined,
        imageUrl: undefined,
        content: '',
        status: null,
        summary: undefined
      },
      dialogFormVisible: false,
      dialogStatus: '',
      textMap: {
        update: '编辑',
        create: '创建'
      },
      typeOptions: [],
      statusOptions: [],
      rules: {
        type: [{ required: true, message: '请选择新闻类型', trigger: 'change' }],
        title: [{ required: true, message: '请输入新闻标题', trigger: 'blur' }],
        imageUrl: [{ required: true, message: '请上传封面大图', trigger: 'blur' }],
        content: [{ required: true, message: '请输入描述信息', trigger: 'blur' }],
        summary: [{ required: true, message: '请输入新闻摘要', trigger: 'blur' }]
      },
      editorOption: {
        placeholder: '上传图片尺寸不要超过1200'
      }
    }
  },
  filters: {
    typeFilter(type) {
      const typeMap = {
        1: '资讯',
        2: '活动',
        3: '公告',
        4: '问答',
        5: '评测',
        6: '视频'
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
    this.getTypeOptions()
    this.getStatusOptions()
    this.getList()
  },
  methods: {
    onEditorChange({ editor, html, text }) {
      // console.log('editor change!', editor, html, text)
      this.tempNews.content = html
    },
    getList() {
      fetchNewsList(this.listQuery).then(response => {
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
      this.tempNews = {
        id: undefined,
        type: undefined,
        title: undefined,
        imageUrl: undefined,
        content: '',
        status: null,
        summary: undefined
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
          this.tempNews.status = status
          this.tempNews.imageUrl = this.imgBig
          createNews(this.tempNews).then(response => {
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
      getNews(tempData).then(response => {
        if (response.data.code === 1) {
          this.tempNews = Object.assign({}, response.data.data)
          this.imgBig = response.data.data.imageUrl
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
          const tempData = Object.assign({}, this.tempNews)
          tempData.status = status
          tempData.imageUrl = this.imgBig
          updateNews(tempData).then(response => {
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
      deleteNews(tempData).then((response) => {
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
      stickNews(tempData).then((response) => {
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
      soldOutNews(tempData).then((response) => {
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
    handleBigAvatarSuccess(res, file) {
      this.tempNews.imageUrl = URL.createObjectURL(file.raw)
      this.imgBig = res.data.fileUrl
    },
    upload() {
      return process.env.BASE_API + '/portal/public/fileUploading'
    }
  }
}
</script>

<style scoped>
  .avatar {
    width: 450px;
    height: 150px;
    display: block;
  }
</style>
