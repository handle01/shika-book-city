<template>
  <el-container>
    <el-aside width="200px">
      <el-row>
        <el-col :span="24">
          <el-image
            style="
              width: 100px;
              height: 100px;
              display: block;
              margin: 40px auto;
            "
            fit="fill"
            :src="require('@/assets/image/logo.png')"
            
          ></el-image>
          <el-menu
            active-text-color="#ffd04b"
            background-color=" #b88230"
            class="el-menu-vertical-demo"
            default-active="1"
            text-color="#fff"
          >
           <el-menu-item index="1" @click="open('isAdded')">
              <el-icon></el-icon>
              <span>书籍管理</span>
            </el-menu-item>
            <el-menu-item index="2" @click="open('noDie')">
              <el-icon></el-icon>
              <span>活动设置</span>
            </el-menu-item>
            <el-menu-item index="3" @click="open('send')">
              <el-icon></el-icon>
              <span>订单管理</span>
            </el-menu-item>
            <el-sub-menu index="4" >
              <template #title>
                <el-icon></el-icon>
                <span>销量统计</span>
              </template>
             <el-sub-menu index="1-1">
            <template #title>书籍销量</template>
            <el-menu-item index="1-1-1"  @click="open('dailySales')">单日销量</el-menu-item>
            <el-menu-item index="1-1-2"  @click="open('monthlySales')">单月销量</el-menu-item>
            <el-menu-item index="1-1-3"  @click="open('yearSales')">年销量</el-menu-item>
          </el-sub-menu>
          <el-sub-menu index="1-2">
            <template #title>营销额</template>
            <el-menu-item index="1-2-1"  @click="open('commodityMarketingAmount')">单月营销额</el-menu-item>
            <el-menu-item index="1-2-2"  @click="open('yearMarketingAmount')">年营销额</el-menu-item>
          </el-sub-menu>
            </el-sub-menu>
            <el-menu-item index="5"  @click="open('bookComment')">
              <el-icon></el-icon>
              <span>客户评论</span>
            </el-menu-item>
             <el-menu-item index="6"  @click="open('config')">
              <el-icon></el-icon>
              <span>个人中心</span>
            </el-menu-item>
          </el-menu>
        </el-col>
      </el-row>
    </el-aside>
    <el-container>
      <el-header
        >
        <div style="
              float: right;
              margin-top: 25px;"><el-button type="danger" @click="toLogout">登出</el-button></div>
        <el-image
            style="
              width: 50px;
              height: 50px;
              float: right;
              margin-top: 20px;
              margin-right: 20px;
            " fit="fill"
            :src="imageUrl"
          ></el-image><div style="
              float: right;
              height: 80px; line-height: 80px;
              margin-right: 80px;">昵称: {{nickName}}</div></el-header
      > 
      <!--进行路由跳转-->
      <el-main>
        <router-view @func="getData"></router-view>
      </el-main>
    </el-container>
  </el-container>
</template>
<script>

export default {
  name: "main",
  data() {
    return {
     userName: storage.getSessionString("userName"),
     nickName:storage.getSession("nickName"),
     imageUrl:storage.getSessionString("profile")
    };
  },
  created: function () {
    this.$router.push({ name: "isAdded" });
  },
  methods: {
     getData(data) {
      this.imageUrl = data.profile;
      this.nickName = data.nickName;
    },
    
  },
};

</script>





<script setup>
//组合式api
import { useStore } from "vuex";
import { useRouter } from "vue-router";
import { ElMessage } from "element-plus";
import storage from '@/util/storage'
let store = useStore();
let router = useRouter();
//登出
let toLogout = function () {
  store.dispatch("LOGOUT").then((res) => {
    if(res.data.flag === false){
       ElMessage.error(res.data.data.message);
    }
    if(res.data.flag === true){
       router.push({ name: "login" });
    ElMessage({
      showClose: true,
      message: "登出成功",
      type: "success",
    });
    }

    
  });
};
//路由跳转
let open = function(routerName){
    router.push({name:routerName})
}

</script>

<style scoped>
.el-container {
  height: 100%;
  width: 100%;
}

.el-header {
  height: 80px;
  background:  #eebe77;
}

.el-footer {
  line-height: 60px;
}

.el-aside {
  background-color:#E6A23C;
  color: var(--el-text-color-primary);
  text-align: center;
  line-height: 200px;
}
.el-button{
  margin-bottom: 300;
  float: right;
}

.el-main {
  background-color: #fcfcfc;
  color: var(--el-text-color-primary);
  text-align: center;
}
</style>