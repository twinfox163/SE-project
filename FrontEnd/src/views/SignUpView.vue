<template>
    <div>
    用户名<input type="text" v-model="username" placeholder="3到12位字母数字"><br>
    密码<input type="password" v-model="password" placeholder="8到16位字母数字"><br>
    <button @click="signup()">sign up</button>
    <router-link to="/login">log in</router-link>
    </div>
</template>

<script setup>
    import axios from 'axios'
    import { ref } from 'vue';
    const username = ref("");
    const password = ref("");
    const signup=()=>{
        const url = import.meta.env.VITE_API_BASE_URL+"/signup";
        const params = {params:{username:username.value,password:password.value}};
        console.log(url,params);
        axios.get(url,params).then(response=>{
            const {status,data} = response;
            console.log(response);
            alert(data);
        })
    }
</script>