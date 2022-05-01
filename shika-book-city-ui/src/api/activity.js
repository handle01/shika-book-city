
import request from "@/api"
//获取活动进行中的数据
export function getIsDie(data) {
    return request({
        url: '/activity/getIsDie',
        method: 'get',
        params: data
    })
}

//获取上架书籍的数据
export function getAllBook(data) {
    return request({
        url: '/activity/getAllBook',
        method: 'get',
        params: data
    })

   
}
//获取活动已结束的数据
export function getNoDie(data) {
    return request({
        url: '/activity/getNoDie',
        method: 'get',
        params: data
    })
}
//获取未发布活动的数据
export function getPublish(data) {
    return request({
        url: '/activity/getPublish',
        method: 'get',
        params: data
    })
}
//获取待发布活动的数据
export function getPending(data) {
    return request({
        url: '/activity/getPending',
        method: 'get',
        params: data
    })
}
//提交修改活动内容
export function updateConfig(data) {
    return request({
        url: '/activity/updateConfig',
        method: 'post',
        data: data
    })
}
//提交未发布活动的内容
export function publish(data) {
    return request({
        url: '/activity/publish',
        method: 'post',
        data: data
    })
}
//新增活动
export function add(data) {
    return request({
        url: '/activity/add',
        method: 'post',
        data: data
    })

   
}

//删除已结束的活动
export function delActivity(data) {
    return request({
        url: '/activity/delActivity',
        method: 'get',
        params: data
    })
}
//强制停止活动
export function stop(data) {
    
    return request({
        url: '/activity/stop',
        method: 'get',
        params: data
    })

   
}


