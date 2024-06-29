<template>
    <div>
        用户名<input type="text" v-model="username" placeholder="3到12位字母数字"><br>
        密码<input type="password" v-model="password" placeholder="8到16位字母数字"><br>
    <button @click="login()">log in</button>
    <router-link to="/signup">sign up</router-link>
    </div>
</template>

<script setup>
    import axios from 'axios';
    import Cookies from 'js-cookie';
    import { ref } from 'vue';
    import router from '@/router';
    const username=ref("");
    const password=ref("");
    const login=()=>{
        const url = import.meta.env.VITE_API_BASE_URL + "/login";
        const params = {params:{username:username.value,password:password.value}};
        console.log(url,params);
        axios.get(url,params).then(response=>{
            const {status,data}=response;
            console.log(response);
            if(data){
                Cookies.set('token',username.value);
                router.push('/');
            }else{
                alert('登录失败');
            }
        })
    }
</script>