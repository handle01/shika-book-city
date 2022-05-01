
<template>
  <el-card class="box-card sb-card" >
    <h3>个人资料</h3>
    <el-form
      ref="form"
      :model="form"
      label-width="150px"
      class="inline-flex"
      label="left"
      :rules="rules"
    >
      <el-form-item
        label="昵称"
        style="margin: 0 80px; width: 300px; margin-top: 30px"
        prop="nickName"
      >
        <el-input v-model="form.nickName" />
      </el-form-item>
      <el-form-item
        label="性别"
        style="margin: 0 80px; width: 300px; margin-top: 30px"
      >
        <el-select v-model="form.sex">
          <el-option label="男" value="1" />
          <el-option label="女" value="2" />
        </el-select>
      </el-form-item>

      <el-form-item
        label="电话号码"
        style="margin: 0 80px; width: 300px; margin-top: 30px"
        prop="phone"
      >
        <el-input v-model="form.phone" />
      </el-form-item>
      <el-form-item
        label="修改头像"
        style="margin: 0 80px; width: 300px; margin-top: 30px"
      >
        <el-upload
          class="avatar-uploader"
          action="/user/upload"
          :auto-upload="autoUpload"
          name="imgFile"
          :headers="headers"
          :show-file-list="false"
          :on-success="handleAvatarSuccess"
          :before-upload="beforeAvatarUpload"
        >
          <img v-if="imageUrl" :src="imageUrl" class="avatar" />
          <el-icon v-else class="avatar-uploader-icon"
            ><i class="el-icon"
              ><svg viewBox="0 0 1024 1024" xmlns="http://www.w3.org/2000/svg">
                <path
                  fill="currentColor"
                  d="M480 480V128a32 32 0 0 1 64 0v352h352a32 32 0 1 1 0 64H544v352a32 32 0 1 1-64 0V544H128a32 32 0 0 1 0-64h352z"
                ></path></svg></i
          ></el-icon>
        </el-upload>
      </el-form-item>
      <el-form-item>
        <el-button
          @click="onSubmit"
          style="margin: 80px 80px; width: 80px; margin-top: 30px"
          >保存</el-button
        >
      </el-form-item>
    </el-form>
  </el-card>
</template>


<script lang="ts" setup>
</script>
import { Plus } from '@element-plus/icons-vue'
<script>
import { ElMessage } from "element-plus";
import storage from "@/util/storage";

export default {
  name: "login",
  data() {
    return {
      // 用户信息

      form: {
        userName: storage.getSessionString("userName"),
        nickName: "",
        sex: "",
        phone: "",
        profile: storage.getSessionString("profile"),
      },
      imageUrl: "",
      headers: {
        token: storage.getSessionString("token"),
        userName: storage.getSessionString("userName"),
      },

      rules: {
        nickName: [
          {
            required: true,
            message: "用户名不能为空",
            trigger: "blur",
          },
          {
            max: 16,
            min: 1,
            message: "用户名5到16个字符之间",
            trigger: "blur",
          },
        ],
        phone: [
          {
            required: true,
            message: "电话号码不能为空",
            trigger: "blur",
          },
          {
            max: 11,
            min: 11,
            message: "电话号码必须11位",
            trigger: "blur",
          },
        ],
      },
    };
  },
  created: function () {
    this.getData(this.form);
  },
  //表单提交修改用户信息
  methods: {
    
    onSubmit() {
      this.$refs.form.validate((valid) => {
        if (valid) {
          this.form.userName = storage.getSessionString("userName");
          this.$store.dispatch("SUBMIT", this.form).then((res) => {
            if (res.data.flag === true) {
              ElMessage({
                showClose: true,
                message: res.data.message,
                type: "success",
              });
              //提交后返回数据到本地会话中
              this.$store
                .dispatch("GETDATA", storage.getSessionString("userName"))
                .then((res) => {
                  if (res.data.flag === true) {
                    res.data.data = this.form;
                    this.$emit('func',this.form)
                    this.$router.push({name:"isAdded"})
                  }
                  if (res.data.flag === false) {
                    ElMessage.error(res.data.message);
                  }
                });
              
            }
            if (res.data.flag === false) {
              ElMessage.error(res.data.message);
            }
          });
        } else {
          ElMessage.error("格式错误");
        }
      });
    }, //上传成功后执行
    handleAvatarSuccess(response) {
      this.imageUrl = response.data;
      this.form.profile = response.data;
      ElMessage({
        showClose: true,
        message: response.message,
        type: "success",
      });
    }, //
    //上传图片之前执行
    beforeAvatarUpload(rawFile) {
      const isJPG = rawFile.type === "image/jpeg";
      const isLt2M = rawFile.size / 1024 / 1024 < 2;
      if (!isJPG) {
        this.$message.error("上传头像图片只能是 JPG 格式!");
      }
      if (!isLt2M) {
        this.$message.error("上传头像图片大小不能超过 2MB!");
      }
      return isJPG && isLt2M;
    },
    //获取用户信息
    getData(from) {
      this.$store.dispatch("GETDATA", from.userName).then((res) => {
        
        if (res.data.flag === true) {
          this.form = res.data.data;
          this.imageUrl = res.data.data.profile;
          
        }
        if (res.data.flag === false) {
          ElMessage.error(res.data.message);
        }
      });
    },
  },
};
</script>

<style scoped>
.avatar-uploader .avatar {
  width: 178px;
  height: 178px;
  display: block;
}
</style>


<style>
.avatar-uploader .el-upload {
  border: 1px dashed #d9d9d9;
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
  transition: var(--el-transition-duration-fast);
}

.avatar-uploader .el-upload:hover {
  border-color: var(--el-color-primary);
}

.el-icon.avatar-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width: 178px;
  height: 178px;
  text-align: center;
}
.el-form-item__label {
  flex: 0 0 auto;
  text-align: right;
  color: var(--el-text-color-regular);
  line-height: 32px;
  padding: 0 12px 0 0;
  box-sizing: border-box;
  font-size: 15px;
}

.sb-card {
  position: absolute;
  top: 30px;
  left: 0;
  bottom: 0;
  right: 0;
  margin: auto;
  width: 1000px;
  height: 600px;
  padding: 3px;
}
.avatar-uploader .el-upload {
  border: 1px dashed #d9d9d9;
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
}

.avatar-uploader .el-upload:hover {
  border-color: #409eff;
}

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
</style>
