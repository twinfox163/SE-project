<script>
    import axios from 'axios';
    import Cookies from 'js-cookie';
    export default{
        data(){
            return {
                id:"111",
                password:"111"
            }
        },
        methods: {
            login(){
                const baseUrl = import.meta.env.VITE_API_BASE_URL + "/login";
                console.log(baseUrl);
                Cookies.set('id',this.id);
                // const baseUrl = import.meta.env.VITE_API_BASE_URL + "/login";
                // console.log(baseUrl);
                // axios.get(baseUrl,{
                //     params :{
                //         id:this.id,
                //         //username: this.name,
                //         password: this.password
                //     }
                // })
                // .then(response=>{
                //     Cookies.set('id',this.id);
                //     const { status, data } = response;
                //     console.log(response)
                //     if(status==200){
                //         if(data.success){
                //             alert("登录成功")
                //             Cookies.set('id',this.id);    
                //         }
                //         else{
                //             alert("登录失败")
                //         }
                //     }
                //     else{
                //         alert("请求失败",status);
                //     }             
                // })    
            }
        }
    }


</script>

<template>
    <div>
    <!-- 用户名:<input type="text" v-model="name"><br> -->
    账号: <input type="text" v-model="id"><br>
    密码: <input type="text" v-model="password"><br>
    <button @click="login()">log in</button>
    <router-link to="/signup">sign up</router-link>
    </div>
</template>