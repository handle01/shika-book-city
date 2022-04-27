export default {
    //数据保存到本地会话
    saveSessionStorage(key, value) {
        localStorage.setItem(key, value);
    },
     //获取本地会话数据
    getSessionString(key) {
        return window.localStorage.getItem(key);
    },
    //数据保存到当前会话
    saveSession(key, value) {
        sessionStorage.setItem(key, value);
    },
    //获取当前会话数据
    getSession(key) {
        return window.sessionStorage.getItem(key);
    },
     //数据对象保存到本地会话
    saveSessionObject(key, value) {
        window.localStorage.setItem(key, JSON.stringify(value));
    },
    //数据对象保存到当前会话
    getSessionObject(key) {
        return JSON.parse(window.localStorage.getItem(key));
    },
    //移除本地会话的数据
    remove(key) {
        return window.localStorage.removeItem(key);
    }
}