<template>
  <div class="bg-content">
    <div class="home-title">富文本编辑器器</div>
    <div class="page-content">
      <div class="editor-box">
        <quill-editor ref="myTextEditor" v-model="content" :config="editorOption"></quill-editor>
        <el-button class="editor-btn" type="primary" @click="submit">提交</el-button>
      </div>
    </div>
  </div>
</template>

<script>
  import { quillEditor } from 'vue-quill-editor'
  import {Utils} from '../../common/util.js'
  let utils = new Utils()
  export default {
    name: 'editor',
    data: function(){
      return {
        content: '<p>Hello BBK</p>',
        editorOption: {
          // something config
        }
      }
    },
    components: {
      quillEditor
    },
    methods: {
      onEditorChange({ editor, html, text }) {
        this.content = html;
      },
      submit(){
        console.log(this.content);
        this.$message.success('提交成功！');
      }
    },
    computed: {
      editor() {
        return this.$refs.myTextEditor.quillEditor;
      }
    }
  }
</script>
<style scoped>
  .editor-box{
    background-color: #fff;
    width: 100%;
    padding: 24px;
    border: 1px solid #ebebeb;
    border-radius: 3px;
  }
  .editor-btn{
      margin-top: 20px;
  }
</style>