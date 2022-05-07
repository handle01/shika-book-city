<template>
  <el-tabs v-model="activeName" class="demo-tabs" @tab-click="handleClick">
    <el-tab-pane  v-for="(pages, index) in pages"
        v-bind:key="index"
        :label="pages.label"
        :name="pages.name"></el-tab-pane>
    <router-view  v-if="pages.name == nowPage"></router-view>

  </el-tabs>
</template>
<script  setup>
import { useRouter } from "vue-router";
import {  ref } from "vue";

const activeName = ref('isAdded')
let router = useRouter()
let nowPage = ref()
let pages = [
  {
    label:"已上架",
    name:"isAdded"
  },{
    label:"未上架",
    name:"noAdded"
  }
]

const handleClick = (tab) => {
    nowPage = tab.name;
    router.push(tab.props.name)

}
</script>
<style>
.demo-tabs > .el-tabs__content {
  padding: 32px;
  color: #6b778c;
  font-size: 32px;
  font-weight: 600;
}
</style>