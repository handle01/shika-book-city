<template>

  <div id="charts" style="width:1500px;height:500px;margin:auto;"></div>
</template>

<script setup>
// 引入echarts
import * as echarts from 'echarts'
import {onMounted,onBeforeMount} from "vue";

import storage from "@/util/storage";
import { getMonthly} from "@/api/sales";

let daily = [];

onBeforeMount(()=>{
  
})
    onMounted(() => { 
      
      let userId = storage.getSessionString("id")
   

      getMonthly({id:userId}).then(res=>{

         daily = res.data.data
         //------------------------------------
           // 需要在页面加载完毕后展示图表，这里使用Vue3的组合式生命周期钩子 onMounted()
     let charts = echarts.init(document.getElementById("charts"));
// 设置图表配置项,可以直接从Echarts的示例中，将配置项复制下来后放入下列的myChart.setOption()中实现图表的样式更换
      charts.setOption( {
  title: {
    text: '单月销量',

    left: 'center'
  },
  tooltip: {
    trigger: 'item'
  },
  legend: {
    orient: 'vertical',
    left: 'left'
  },
  series: [
    {
      name: 'Access From',
      type: 'pie',
      radius: '50%',
      data:daily,
      emphasis: {
        itemStyle: {
          shadowBlur: 10,
          shadowOffsetX: 0,
          shadowColor: 'rgba(0, 0, 0, 0.5)'
        }
      }
    }
  ]
});
            window.onresize = function () { // 自适应大小
              charts.resize();
            };
            
      });
         
      })
      
     
    

</script>

<style>

element.style{
  width:1500px;height:500px;margin:auto
}
</style>
