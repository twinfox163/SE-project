<script setup>
    import { computed, onMounted, reactive, watch } from 'vue';
    import Cookies from 'js-cookie'
    import axios from 'axios'
    
    import Profile from '@/components/Profile.vue'
    import Directory from '@/components/Directory.vue'
    
    const userdata=reactive({
        username:Cookies.get('username')||0,
        repositories:[{name:null,children:[],files:[],directory:""}]
    })
    onMounted(()=>{
        userdata.username=Cookies.get('username')||0;
        if(userdata.username){
            const url=import.meta.env.VITE_API_BASE_URL+'/stores';
            const params={params:{username:userdata.username}};
            console.log(url,params);
            axios.get(url,params).then(response=>{
                const {status,data}=response;
                console.log(response);
                userdata.repositories=data;
            })
        } 
    })
    watch(
        ()=>Cookies.get('username'),
        ()=>{
            userdata.username=Cookies.get('username')||0;
            if(userdata.username){
                const url=import.meta.env.VITE_API_BASE_URL+'/stores';
                const params={params:{username:userdata.username}};
                console.log(url,params);
                axios.get(url,params).then(response=>{
                    const {status,data}=response;
                    console.log(response);
                    userdata.repositories=data;
                })
            } 
        }
    )
    // const update=()=>{
    //     userdata.username=Cookies.get('username')||0;
    //     if(userdata.username){
    //         const url=import.meta.env.VITE_API_BASE_URL+'/stores';
    //         const params={params:{username:userdata.username}};
    //         console.log(url,params);
    //         axios.get(url,params).then(response=>{
    //             const {status,data}=response;
    //             console.log(response);
    //             userdata.repositories=data;
    //         })
    //     }
    // }
</script>

<template>
    <div class="home-view">
        <div class="left-panel">
            <div>
                <Profile :user="userdata"></Profile>
            </div>
            <div>
                现在的仓库号是 0 <br>
                <div v-for="repo in userdata.repositories" :key="repo.name">
                    <Directory :item="repo"/>
                </div>
            </div>
        </div>
        <div class="right-panel">
            file
        </div>
    </div>
</template>

<style scoped>
.home-view {
  display: flex;
  width: 100%;
  height: 100%;
}

.left-panel {
  flex: 1;
  width: 10%;
  background-color: #f8f8f8;
  padding: 1rem;
}

.right-panel {
  flex: 5;
  width: 50%;
  background-color: #ffffff;
  padding: 1rem;
}
</style>