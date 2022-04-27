/**
 * axios的基本api
 * // 发送 POST 请求
 * axios({
 *  method: 'post',
 *  url: '/user/12345',
 *  data: {
 *    firstName: 'Fred',
 *    lastName: 'Flintstone'
 *  }
 *});
 *
 */

import { ElMessage } from "element-plus";
import axios from 'axios'
import store from '@/store'
import storage from '@//util/storage'
import router from '@/router';


// 创建axios实例
const request = axios.create({
  // axios中请求配置有baseURL选项，表示请求URL公共部分
  baseURL: 'http://127.0.0.1:80/',
  // 超时
  timeout: 10000,
  // 设置Content-Type，规定了前后端的交互使用json
  headers: { 'Content-Type': 'application/json;charset=utf-8', 'Access-Contorl-Allow-Oring': '*' }
})

// 函数返回唯一的请求key
function getRequestKey(config) {
  let { method, url, params, data } = config;
  return [method, url, JSON.stringify(params), JSON.stringify(data)].join("&");
}

// 添加请求信息
let pendingRequest = new Map();
function addPendingRequest(config) {
  let requestKey = getRequestKey(config);
  config.cancelToken = config.cancelToken || new axios.CancelToken((cancel) => {
    if (!pendingRequest.has(requestKey)) {
      pendingRequest.set(requestKey, cancel);
    }
  });
}


// 取消重复请求，移除重复请求信息
function removePendingRequest(config) {
  let requestKey = getRequestKey(config);
  if (pendingRequest.has(requestKey)) {
    // 如果是重复的请求，则执行对应的cancel函数
    let cancel = pendingRequest.get(requestKey);
    cancel(requestKey);
    // 将前一次重复的请求移除
    pendingRequest.delete(requestKey);
  }
}


// 添加请求拦截器
request.interceptors.request.use(function (config) {
  // 检查是否存在重复请求，若存在则取消已发的请求
  removePendingRequest(config);
  // 把当前请求信息添加到pendingRequest对象中
  addPendingRequest(config);
  // 在发送请求之前携带登录信息

  config.headers['token'] = storage.getSessionString("token")
  config.headers['userName'] = storage.getSessionString("userName")// 让每个请求携带自定义token 请根据实际情况自行修改
  return config;
}, function (error) {
  // 对请求错误做些什么
  return Promise.reject(error);
});
// http response 拦截器
request.interceptors.response.use(

  response => {
    // 从pendingRequest对象中移除请求
    removePendingRequest(response.config);
    return response;
  },
  error => {
    // 从pendingRequest对象中移除请求
    removePendingRequest(error.config || {});
    if (error.response) {
      switch (error.response.status) {
        case 401:
          // 返回 401 清除token信息并跳转到登录页面
          store.commit("SAVE_USERNAME", '')
          store.commit("SAVE_NICKNAME", '')
          store.commit("SAVE_TOKEN", '')
          storage.remove("loginUser")
          storage.remove("token")
          router.push({ name: "login" })
          ElMessage.error("您已在别地登录");
      }
    }
    
    return Promise.reject(error)   // 返回接口返回的错误信息
  });




export default request