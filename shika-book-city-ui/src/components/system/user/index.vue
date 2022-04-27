<template>
  <el-table :data="filterTableData" style="width: 100%">
    <el-table-column label="userName" prop="userName" />
    <el-table-column label="nickName" prop="nickName" />
    <el-table-column align="right">
      <template #header>
        <el-input v-model="search" size="small" placeholder="Type to search" />
      </template>
      <template #default="scope">
        <el-button size="small" @click="handleEdit(scope.$index, scope.row)"
          >Edit</el-button
        >
        <el-button
          size="small"
          type="danger"
          @click="handleDelete(scope.$index, scope.row)"
          >Delete</el-button
        >
      </template>
    </el-table-column>
  </el-table>
    <el-pagination
    :page-size="1"
    layout="prev, pager, next:" @current-change="changPage"
    :total="2"
  />
</template>

<script  setup>
import { computed, ref } from "vue";
import { getPage } from "@/api/user";
import { onMounted } from "vue";
var tableData = ref([]);
onMounted(() => {
  getPage(page).then((res) => {
    tableData.value = res.data;
  });
});

var page = {
  offset: 0,
  limit: 2,
};
let changPage = function(page){
  getPage({
  offset: page -1,
  limit: 1-page,
}).then((res) => {
    tableData.value = res.data;
  });
}
// const list = tableData.value;

let search = ref("");
let filterTableData = computed(() =>
  tableData.value.filter(
    (data) =>
      !search.value ||
      data.name.toLowerCase().includes(search.value.toLowerCase())
  )
);
const handleEdit = () => {};
const handleDelete = () => {};
</script>