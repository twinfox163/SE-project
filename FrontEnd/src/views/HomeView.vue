<script setup>
    import { computed, onMounted, reactive, watch, ref } from 'vue';
    import Cookies from 'js-cookie'
    import axios from 'axios'

    import Profile from '@/components/Profile.vue'
    import Directory from '@/components/Directory.vue'

    import {g_data} from '@/store.js'

    const profile=reactive({
        username:Cookies.get('username')||0,
        repositories:[{name:null,children:[],files:[],directory:""}]
    })
    onMounted(()=>{
        profile.username=Cookies.get('username')||0;
        if(profile.username){
            const url=import.meta.env.VITE_API_BASE_URL+'/stores';
            const params={params:{username:profile.username}};
            console.log(url,params);
            axios.get(url,params).then(response=>{
                const {status,data}=response;
                console.log(response);
                profile.repositories=data;
            })
        } 
    })
    watch(
        ()=>Cookies.get('username'),
        ()=>{
            profile.username=Cookies.get('username')||0;
            if(profile.username){
                const url=import.meta.env.VITE_API_BASE_URL+'/stores';
                const params={params:{username:profile.username}};
                console.log(url,params);
                axios.get(url,params).then(response=>{
                    const {status,data}=response;
                    console.log(response);
                    profile.repositories=data;
                })
            } 
        }
    )
    const target_repo = computed(()=>{
        return profile.repositories.filter(item=>{
            return item.directory == g_data.repo_url;
        })
    })

</script>

<template>
    <div class="home-view">
        <div class="left-panel">
            <div>
                <Profile :profile="profile"></Profile>
            </div>
            <div>
                <!-- <el-text size="large">现在的仓库号是 0</el-text>   <br> -->
                现在的repo_url: <br> {{ g_data.repo_url }}
                <div v-for="repo in target_repo" :key="repo.name">
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