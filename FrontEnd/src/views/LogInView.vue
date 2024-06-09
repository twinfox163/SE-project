<script>
    import axios from 'axios';
    import Cookies from 'js-cookie';
    export default{
        data(){
            return {
                //id:"111",
                password:"111",
                username:"0123456789"
            }
        },
        methods: {
            login(){
                const baseUrl = import.meta.env.VITE_API_BASE_URL + "/login";
                console.log(baseUrl);
                axios.get(baseUrl,{
                    params:{
                        username:this.username,
                        password:this.password
                    }
                })
                .then((response)=>{
                    const {status,data}=response;
                    if(data){
                        //成功
                        //alert("登录成功");
                        Cookies.set('username',this.username);
                        this.$router.push('/');
                    }
                    else{
                        alert("登录失败");
                    }
                })
            }
        }
    }


</script>

<template>
    <div>
    用户名: <input type="text" v-model="username"><br>
    <!-- 账号：<input type="text" v-model="id"><br> -->
    密码: <input type="text" v-model="password"><br>
    <button @click="login()">log in</button>
    <router-link to="/signup">sign up</router-link>
    </div>
</template>