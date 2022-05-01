<template>
  <el-table :data="filterTableData" style="width: 100%">
    <el-table-column label="封面">
      <template #default="scope">
        <img :src="scope.row.cover" style="height: 50px" />
      </template>
    </el-table-column>

    <el-table-column label="活动名称" prop="activityName" />
    <el-table-column label="价格（单位：元）" prop="price" />
    <el-table-column label="开始时间" prop="startTime" />
    <el-table-column label="截止时间" prop="activityDeadline" />

    <el-table-column align="right">
      <template #header>
        <el-input v-model="search" size="small" placeholder="Type to search" />
      </template>
      <template #default="scope">
        <el-button size="small" type="danger" @click="Delete(scope.row.id)"
          >删除</el-button
        >
      </template>
    </el-table-column>
  </el-table>
  <el-pagination
    small
    background
    layout="prev, pager, next"
    :total="total"
    class="mt-4"
    :page-size="pageSize"
    @current-change="changPage"
  />
</template>
<script  setup>
import { ElMessage, ElMessageBox } from "element-plus";
import { computed, ref } from "vue";
import { getAllBook, getIsDie, delActivity } from "@/api/activity";
import { onMounted } from "vue";

var tableData = ref([]); //列表数据
var total = ref(0); //数据总条数
var pageSize = ref(0); //页面最大展现条数

import storage from "@/util/storage";

let get = ref({
  size: 10,
  current: 1,
  userName: storage.getSessionString("userName"),
});
//书籍数据
let allBook = ref([]);
//页面跳转渲染数据
onMounted(() => {
  
  getIsDie(get.value).then((res) => {
    tableData.value = res.data.data.list;
    pageSize.value = res.data.data.pageSize;
    total.value = res.data.data.total;
  });
  let data = ref({
    userName: storage.getSessionString("userName"),
  });
  getAllBook(data.value).then((res) => {
    allBook.value = res.data.data;
  });
});

let currentPage = 1;
//切换页
let changPage = function (page) {
  currentPage = page;
  getIsDie({
    current: page,
    size: 10,
    userName: storage.getSessionString("userName"),
  }).then((res) => {
    tableData.value = res.data.data.list;
  });
};
// const list = tableData.value;

let search = ref("");
//搜索
let filterTableData = computed(() =>
  tableData.value.filter(
    (data) =>
      !search.value ||
      data.name.toLowerCase().includes(search.value.toLowerCase())
  )
);

//删除
const Delete = (data) => {
  ElMessageBox.confirm("是否要删除该活动?", "提示", {
    confirmButtonText: "确认",
    cancelButtonText: "取消",
    type: "warning",
  })
    .then(() => {
      var id = {
        activityId: data,
        userId: storage.getSessionString("id"),
      };
      delActivity(id).then((res) => {
        if (res.data.flag === true) {
          ElMessage.success(res.data.message);
          getIsDie({
            size: 10,
            current: currentPage,
            userName: storage.getSessionString("userName"),
          }).then((res) => {
            tableData.value = res.data.data.list;
            pageSize.value = res.data.data.pageSize;
            total.value = res.data.data.total;
          });
        } else {
          ElMessage.error(res.data.message);
        }
      });
    })
    .catch(() => {
      ElMessage({
        type: "info",
        message: "取消成功",
      });
    });
};
</script>
<style >
.z {
  z-index: 10001 !important;
}
.avatar {
  width: 178px;
  height: 178px;
  display: block;
}
.el-input__inner {
  -webkit-appearance: none;
  background-color: var(--el-input-bg-color, var(--el-fill-color-blank));
  background-image: none;
  border-radius: var(--el-input-border-radius, var(--el-border-radius-base));
  box-sizing: border-box;
  color: var(--el-input-text-color, var(--el-text-color-regular));
  display: inline-block;
  font-size: inherit;
  height: 32px;
  line-height: 32px;
  outline: 0;
  padding: 0 11px;
  transition: var(--el-transition-box-shadow);
  width: 500%;
  box-shadow: 0 0 0 1px var(--el-input-border-color, var(--el-border-color))
    inset;
  border: none;
}
.avatar-uploader .el-upload {
  width: 120px;
  height: 120px;
  border: 1px dashed #d9d9d9;
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
  transition: var(--el-transition-duration-fast);
}
</style>

