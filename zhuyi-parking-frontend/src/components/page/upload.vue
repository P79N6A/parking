<template>
  <div class="bg-content">
    <div class="home-title">上传图片</div>
    <div class="page-content">
      <div class="upload-box">
        <div class="head">
          上传图片
          <em>只能上传jpg文件，且不超过2MB!</em>
        </div>
        <el-upload
          class="avatar-uploader"
          action="https://jsonplaceholder.typicode.com/posts/"
          :show-file-list="false"
          :on-success="handleAvatarSuccess"
          :before-upload="beforeAvatarUpload">
          <img v-if="imageUrl" :src="imageUrl" class="avatar">
          <i v-else class="el-icon-plus avatar-uploader-icon"></i>
        </el-upload>
      </div>
    </div>
  </div>
</template>

<script>
  export default {
    name: 'upload',
    data() {
      return {
        imageUrl: ''
      };
    },
    methods: {
      handleAvatarSuccess(res, file) {
        this.imageUrl = URL.createObjectURL(file.raw);
      },
      beforeAvatarUpload(file) {
        const fileType = ['image/png','image/jpeg']
        const isJPG = fileType.find( ( value ) => {
          return file.type == value
        } )
        const isLt2M = file.size / 1024 / 1024 < 2

        if (!isJPG) {
          this.$message.error('上传头像图片只能是 JPG 格式!')
        }
        if (!isLt2M) {
          this.$message.error('上传头像图片大小不能超过 2MB!')
        }
        return isJPG && isLt2M
      }
    }
  }
</script>

<style scoped>
  .avatar-uploader-icon {
    font-size: 28px;
    color: #8c939d;
    width: 178px;
    height: 178px;
    line-height: 178px;
    text-align: center;
  }
  .avatar {
    width: 178px;
    height: 178px;
    display: block;
  }
  .upload-box{
    background-color: #fff;
    width: 100%;
    padding: 24px;
    border: 1px solid #ebebeb;
    border-radius: 3px;
  }
  .head{
    font-size: 14px;
    padding-bottom: 15px;
  }
</style>
