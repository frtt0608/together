import Vue from 'vue'
import Router from 'vue-router'
import HelloWorld from '@/components/HelloWorld'
import 'vuetify/dist/vuetify.min.css' // Ensure you are using css-loade
import index from '@/views/index'
import tour from '@/views/tour'
import register from '@/views/register'
import tour_views from '@/views/tour_views'
import login from '@/views/login'
import tour_detail from '@/views/tour_detail'
import border from '@/views/border'
import showDetail from '@/views/showDetail'
import mypage from '@/views/mypage'
import schedule from '@/views/schedule'
import test from '@/views/test'
import VueSession from 'vue-session'
import Vuetify from 'vuetify'

Vue.use(VueSession)
Vue.use(Vuetify)
Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'index',
      component: index
    },
    {
      path: '/tour',
      name: 'tour',
      component: tour
    },
    {
      path: '/tour_views',
      name: 'tour_views',
      component: tour_views
    },
    {
      path: '/tour_detail/:id/:id_2',
      name: 'tour_detail',
      component: tour_detail
    },
    {
     path: '/register',
     name: 'register',
     component: register
   },
   {
    path: '/login',
    name: 'login',
    component: login
  },
  {
   path: '/showDetail/:id/',
   name: 'freedetail',
   component: showDetail
  },
  {
   path: '/border',
   name: 'border',
   component: border
  },
  {
   path: '/mypage',
   name: 'mypage',
   component: mypage
  },
  {
   path: '/test',
   name: 'test',
   component: test
  },
  {
   path: '/schedule',
   name: 'schedule',
   component: schedule
  },
  ]
})
