import request from '@/api'

export function getBook(data) {
    return request({
        url: '/bookComment/getBook',
        method: 'get',
        params: data
    })  
}

export function getBookComment(data) {
    return request({
        url: '/bookComment/getBookComment',
        method: 'get',
        params: data
    })  
}