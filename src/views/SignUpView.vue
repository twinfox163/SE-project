<script>
    import axios from 'axios';
    export default{
        data(){
            return {
                name:"hello",
                //id:"111",
                password:"111"
            }
        },
        methods: {
            signup(){
                const baseUrl = import.meta.env.VITE_API_BASE_URL + "/signup";
                console.log(baseUrl);
                axios.get(baseUrl,{
                    params :{
                        //id:this.id,
                        username: this.name,
                        password: this.password
                    }
                })
                .then(response=>{
                    const {status,data} = response;
                    alert(data);
                })    
            },
        }
    }


</script>

<template>
    <div>
    用户名:<input type="text" v-model="name"><br>
    <!-- 账号: <input type="text" v-model="id"><br> -->
    密码: <input type="text" v-model="password"><br>
    <button @click="signup()">注册</button>
    </div>
</template>