<script setup>
    import { computed, onMounted, reactive, watch, ref } from 'vue';
    import Cookies from 'js-cookie'
    import axios from 'axios'

    import Profile from '@/components/Profile.vue'
    import Directory from '@/components/Directory.vue'
    import FileShow from '@/components/FileShow.vue';

    import {g_data} from '@/store.js'

    //用户主页数据
    const profile=reactive({
        username:Cookies.get('username')||0,
        repositories:[]
    })
    //初始化
    onMounted(()=>{
        g_data.repo_url = null;
        g_data.file_url = null;
        g_data.dir_url = null;
        file_content.value = null;
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
    //计算属性求当前的库
    const target_repo = computed(()=>{
        return profile.repositories.filter(item=>{
            return item.directory == g_data.repo_url;
        })
    })
    
    //监视file_url更新file_content
    watch(
        ()=>g_data.file_url,
        ()=>{
            load_file();
        }
    )
    const file_content = ref("");
    const load_file=(()=>{
        if(g_data.file_url){
            const url=import.meta.env.VITE_API_BASE_URL+'/files';
            const params={params:{filename:g_data.file_url}};
            console.log(url,params);
            axios.get(url,params).then(response=>{
                const {status,data}=response;
                console.log(response);
                file_content.value=data;
            })
        }
    })

</script>

<template>
    <div class="home-view">
        <div class="left-panel">
            <div>
                <Profile :profile="profile"></Profile>
            </div>
            <div>
                现在的repo_url: <br> {{ g_data.repo_url }} <br>
                现在的dir_url: <br> {{ g_data.dir_url }} <br>
                <div v-for="repo in target_repo" :key="repo.name">
                    <Directory :item="repo"/>
                </div>
            </div>
        </div>
        <div class="right-panel">
            <FileShow :file_content="file_content" :key="file_content"/>
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