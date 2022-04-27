<template>
  <el-container>
    <el-aside width="790px"
      ><el-image
        fit="fill"
        :src="require('@/assets/image/login.jpg')"
      ></el-image
    ></el-aside>
    <el-main>
      <el-card class="box-card login-card">
        <span class="login-title">个人书城管理系统</span>
        <span class="login-tip">注册页面</span>
        <el-form ref="user" :model="user" label-width="120px" :rules="rules">
          <el-form-item label="用户名" prop="userName">
            <el-input
              v-model="user.userName"
              placeholder="请输入用户"
            ></el-input>
          </el-form-item>
          <el-form-item label="密码" prop="password">
            <el-input
              v-model="user.password"
              type="password"
              placeholder="请输入密码"
              show-password
            ></el-input>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="doregister">注册</el-button>
          </el-form-item>
        </el-form>
        
      </el-card>
    </el-main>
  </el-container>
</template>

<script>
import { ElMessage } from "element-plus";
export default {
  name: "login",
  data() {
    return {
      // 用户信息
      user: {
        userName: "",
        password: "",
      },
      rules: {
        userName: [
          {
            required: true,
            message: "用户名不能为空",
            trigger: "blur",
          },
          {
            max: 16,
            min: 5,
            message: "用户名5到16个字符之间",
            trigger: "blur",
          },
        ],
        password: [
          {
            required: true,
            message: "密码不能为空",
            trigger: "blur",
          },
          {
            max: 16,
            min: 5,
            message: "密码6到16个字符之间",
            trigger: "blur",
          },
        ],
      },
    };
  },
  methods: {
    //注册功能
    doregister() {
      this.$refs.user.validate((valid) => {
        if (valid) {
          this.$store.dispatch("REGISTER", this.user).then((res) => {
            if (res.data.flag === true) {
              this.$router.push({ name: "login" });
              ElMessage({
                showClose: true,
                message: res.data.message,
                type: "success",
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
    },
    register(){
      this.$router.push({ name: "main" });
    }
  },
};
</script>

<style scope>
.el-image {
  height: 800px;
  width: 750px;
}
.el-main {
  position: relative;
}

.login-card {
  position: absolute;
  top: 0;
  left: 0;
  bottom: 0;
  right: 0;
  margin: auto;
  width: 480px;
  height: 400px;
  padding: 50px;
}
.login-title {
  width: 459px;
  height: 70px;
  font-size: 40px;
  font-family: AlibabaPuHuiTiB;
  color: #333333;
  line-height: 90px;
  letter-spacing: 1px;
  font-weight: 800;
  display: block;
  text-align: left;
}
.login-tip {
  width: 319px;
  height: 70px;
  font-size: 30px;
  font-family: PingFangSC-Regular, PingFang SC;
  font-weight: 400;
  color: #999999;
  line-height: 90px;
  letter-spacing: 1px;
  display: block;
  text-align: left;
  margin-bottom: 30px;
}
</style>