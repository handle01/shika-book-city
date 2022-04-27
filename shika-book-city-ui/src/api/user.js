import request from '@/api'

// 登录功能
export function login(data) {
    return request({
        url: '/user/login',
        method: 'post',
        data: data
    })
}
//登出
export function logout() {
    return request({
        url: '/user/logout',
        method: 'get',

    })
}
//注册
export function register(data) {
    return request({
        url: '/user/register',
        method: 'post',
        data: data
    })
}


// }
//提交修改表单
export function submit(data) {
    return request({
        url: '/user/submit',
        method: 'post',
        data: data
    })
}
//获取页面基本数据
export function getData(data) {
    return request({
        url: '/user/getData',
        method: 'post',
        data: data
    })
}


