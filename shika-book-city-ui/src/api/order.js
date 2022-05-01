import request from '@/api'

//获取订单信息
export function getOrder(data) {
    return request({
        url: '/order/getOrder',
        method: 'get',
        params: data
    })
}

//获取配送信息
export function getOrderExpressage(data) {
    return request({
        url: '/order/getOrderExpressage',
        method: 'get',
        params: data
    })
}
//获取签收信息
export function getSigned(data) {
    return request({
        url: '/order/getSigned',
        method: 'get',
        params: data
    })
}

//获取签收信息
export function send(data) {
    return request({
        url: '/order/send',
        method: 'post',
        data: data
    })
}