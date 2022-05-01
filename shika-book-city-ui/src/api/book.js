
import request from "@/api"
//获取页面基本数据
export function getIsAdded(data) {
    return request({
        url: '/book/getIsAdded',
        method: 'get',
        params: data
    })

   
}
//获取未上架的书籍
export function getNoAdded(data) {
    return request({
        url: '/book/getNoAdded',
        method: 'get',
        params: data
    })

   
}
//修改书籍信息
export function submit(data) {
    return request({
        url: '/book/submit',
        method: 'post',
        data: data
    })

   
}
//添加书籍
export function addBook(data) {
    return request({
        url: '/book/add',
        method: 'post',
        data: data
    })

   
}
//删除书籍
export function del(data) {
    
    return request({
        url: '/book/del',
        method: 'post',
        params: data
    })

   
}
//上架
export function isAdded(data) {
    
    return request({
        url: '/book/isAdded',
        method: 'post',
        params: data
    })

   
}
//下架
export function noAdded(data) {
    
    return request({
        url: '/book/noAdded',
        method: 'post',
        params: data
    })

   
}