import Vue from 'vue';
import VueRouter from 'vue-router';
import Hello from '@/components/Hello';
import login from '../components/login/login.vue';
import register from '../components/register/register.vue';
import admin from '../components/admin/admin.vue';
import resident from '../components/resident/resident.vue';
import mur from '../components/mur/mur.vue';
import contact from '../components/contact/contact.vue';
import chat from '../components/chat/chat.vue';
import profil from '../components/profil/profil.vue';
import suggestion from '../components/suggestion/suggestion.vue';
Vue.use(VueRouter)


const routes = [
    {
      name:'login',
      path:'/login',
      alias:'/',
      component:login
    },
    {
      name:'mur',
      path:'/mur',
      component:mur,
       meta: { requiresAuth: true , adminAuth:true , residentAuth : false}
    },
    {
      name:'contact',
      path:'/contact',
      component:contact,
       meta: { requiresAuth: true , adminAuth:true , residentAuth : false}
    },
    {
      name:'suggestion',
      path:'/suggestion',
      component:suggestion,
       meta: { requiresAuth: true , adminAuth:true , residentAuth : false}
    },
    {
      name:'chat',
      path:'/chat',
      component:chat,
       meta: { requiresAuth: true , adminAuth:true , residentAuth : false}
    },
    {
      name:'profil',
      path:'/profil',
      component:profil,
       meta: { requiresAuth: true , adminAuth:true , residentAuth : false}
    },
    {
      name:'register',
      path:'/register',
      component:register
    },
     {
      name:'admin',
      path:'/admin',
      component:admin,
       meta: { requiresAuth: true , adminAuth:true , residentAuth : false}
    },
    {
      name:'resident',
      path:'/resident',
      component:resident,
       meta: { requiresAuth: true , residentAuth :true , adminAuth:false }
    },
    {
      path: '*',
      redirect: '/login',
      component:login
    }
]


const router = new VueRouter({routes,mode:'history'})

router.beforeEach((to, from, next) => {
  if(to.meta.requiresAuth) {
    const authUser = JSON.parse(window.localStorage.getItem('lbUser'))
    if(!authUser || !authUser.token) {
      next({name:'login'})
    }
    else if(to.meta.adminAuth) {
    const authUser = JSON.parse(window.localStorage.getItem('lbUser'))
    if(authUser.data.role_id === 'ADMIN') {
      next()
    }else {
      next('/resident')
    }
  } else if(to.meta.residentAuth) {
    const authUser = JSON.parse(window.localStorage.getItem('lbUser'))
    if(authUser.data.role_id === 'RESIDENT') {
      next()
    }else {
      console.log('Im in admin')
      next('/admin')
    }
  }
  }else {
  next()
  }
})


export default router;
