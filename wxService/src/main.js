import Vue from 'vue'
import App from './App'
import './style.css'

Vue.config.productionTip = false
App.mpType = 'app'

const app = new Vue(App)
app.$mount()

//定义全局变量
Vue.prototype.globalData = getApp().globalData
