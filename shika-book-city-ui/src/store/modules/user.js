import {getData, login, logout, register,submit} from "@/api/user.js"
import storage from '@/util/storage'



const user = {
    state: {
        username: "",
        nickname: "",
        token: ""
    },
    getters: {},
    mutations: {
        SAVE_USERNAME(state, username) {
            state.username = username
        }, SAVE_NICKNAME(state, nickname) {
            state.nickname = nickname
        }, SAVE_TOKEN(state, token) {
            state.token = token
        }

    },
    actions: {
        //登录
        LOGIN({commit},user) {
            return new Promise(function (resolve) {
                login(user).then(res => {
                    
                    if(res.data.flag === true){
                        
                        commit("SAVE_TOKEN",res.data.data.token )
                        commit("SAVE_USERNAME",res.data.data.userName)
                        commit("SAVE_NICKNAME",res.data.data.nickName)
                        storage.saveSession("nickName",res.data.data.nickName)
                        storage.saveSessionStorage("token",res.data.data.token)
                        storage.saveSessionStorage("userName",res.data.data.userName)
                        storage.saveSessionStorage("profile",res.data.data.profile)
                        storage.saveSessionStorage("id",res.data.data.id)
                    }
                    resolve(res)
                })
            })

        },
        //登出
        LOGOUT({commit}) {
            return new Promise(function (resolve) {
                logout(user).then(res => {
                    commit("SAVE_TOKEN", '')
                    storage.remove("token")
                    storage.remove("proFile")
                    storage.remove("proFile")
                    resolve(res)
                })
            })

        },
        //注册 
        REGISTER({commit},user) {
            return new Promise(function (resolve) {
                
                register(user).then(res => {
                    commit
                    resolve(res)
                })
            })

        },
        //提交修改数据
        SUBMIT({commit},user) {
            return new Promise(function (resolve) {
                submit(user).then(res => {
                    commit
                    resolve(res)
                })
            })

        },//获取基础页面数据
        GETDATA({commit},user) {
            return new Promise(function (resolve) {
                getData(user).then(res => {
                        commit("SAVE_TOKEN",res.data.data.token )
                        commit("SAVE_USERNAME",res.data.data.userName)
                        commit("SAVE_NICKNAME",res.data.data.nickName)
                        storage.saveSession("nickName",res.data.data.nickName)
                        storage.saveSessionStorage("profile",res.data.data.profile)
                    resolve(res)
                })
            })

        },
    }
}

export default user