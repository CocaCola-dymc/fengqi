import Vue from 'vue'
import App from './register'
// import mpvueToastRegister from 'mptoast'



Vue.config.productionTip = false


const app = new Vue(App)
// mpvueToastRegister(Vue)
app.$mount()
