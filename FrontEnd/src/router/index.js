import { createRouter, createWebHistory } from 'vue-router'
import Cookies from 'js-cookie';

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'home',
      component: () => import('@/views/HomeView.vue'),
      beforeEnter: (to, from, next) => {
        // 在进入 /dashboard 路由前进行验证
        if (Cookies.get('username')!='') {
          next(); // 继续进入目标路由
        } else {
          next('/login'); // 未认证，跳转到登录页
        }
      },
    },
    {
      path: '/about',
      name: 'about',
      // route level code-splitting
      // this generates a separate chunk (About.[hash].js) for this route
      // which is lazy-loaded when the route is visited.
      component: () => import('@/views/AboutView.vue')
    },
    {
      path: '/signup',
      name: 'Sign up',
      component: () => import('@/views/SignUpView.vue')
    },
    {
      path: '/login',
      name: 'Log in',
      component: () => import('@/views/LogInView.vue')
    },
  ]
})

export default router
