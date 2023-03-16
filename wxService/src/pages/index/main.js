import Vue from 'vue'
import App from './index'
// import axios from 'axios'

// //导入Mint UI
// import MintUI from 'mint-ui'
// //导入样式文件
// import 'mint-ui/lib/style.css'
// //注册为Vue的插件
// Vue.use(MintUI);


Vue.config.productionTip = false
// axios.defaults.baseURL = 'http://127.0.0.1:8080'
// Vue.prototype.axios = axios

const app = new Vue(App)
app.$mount()
