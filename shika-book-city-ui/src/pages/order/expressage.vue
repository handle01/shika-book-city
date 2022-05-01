<template>
  <el-table :data="filterTableData" style="width: 100%">
    

    <el-table-column label="订单号" prop="orderId" />
    <el-table-column label="收货人" prop="nickName" />
    <el-table-column label="电话号码" prop="phone" />
    <el-table-column label="当前位置" prop="position" />
    <el-table-column label="商品信息" prop="commodity" />


    <el-table-column align="right">
      <template #header>
        <el-input v-model="search" size="small" placeholder="Type to search" />
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

import { computed, ref } from "vue";
import { getOrderExpressage} from "@/api/order";
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

//页面跳转渲染数据
onMounted(() => {
  
  getOrderExpressage(get.value).then((res) => {
    tableData.value = res.data.data.list;
    pageSize.value = res.data.data.pageSize;
    total.value = res.data.data.total;
  });
 
 
});


//切换页
let changPage = function (page) {
  getOrderExpressage({
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

