<script setup>
    import { computed, onMounted, reactive, watch, ref } from 'vue';
    import Cookies from 'js-cookie'
    import axios from 'axios'

    import Profile from '@/components/Profile.vue'
    import Directory from '@/components/Directory.vue'
    import FileShow from '@/components/FileShow.vue';
    import AddFileDir from '@/components/AddFileDir.vue';
    import History from '@/components/History.vue'
    import {g_data} from '@/store.js'

    //用户主页数据
    const profile=reactive({
        username:Cookies.get('token')||0,
        repositories:[]
    })
    //初始化
    onMounted(()=>{
        g_data.token = Cookies.get('token');
        g_data.repo_url = null;
        g_data.file_url = null;
        g_data.dir_url = null;
        g_data.cur_url = null;
        file_content.value = null;
        profile.username=Cookies.get('token')||0;
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

    const format_repo_url = computed(()=>{
        if(g_data.repo_url)
        return g_data.repo_url.replace(/->/g,'/');
        else return null;
    })
    const format_dir_url = computed(()=>{
        if(g_data.dir_url)
        return g_data.dir_url.replace(/->/g,'/');
        else return null;
    })
    const format_file_url = computed(()=>{
        if(g_data.file_url)
        return g_data.file_url.replace(/->/g,'/');
        else return null;
    })
    const format_cur_url = computed(()=>{
        if(g_data.cur_url)
        return g_data.cur_url.replace(/->/g,'/');
        else return null;
    })

    const load_repo = () => {
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
    const file_show_flag = computed(()=>{
        if(g_data.cur_show == 'file'){
            return true;
        }else{
            return false;
        }
    })
    const history_show_flag = computed(()=>{
        if(g_data.cur_show == 'history'){
            return true;
        }else{
            return false;
        }
    })
    const show_history = ()=>{
        if(g_data.repo_url){
            g_data.cur_show = 'history';
        }
        else{
            alert('请选择库');
        }       
    }
    const target_history = computed(()=>{
        return target_repo.value[0].history;
    })
</script>

<template>
    <div class="home-view">
        <div class="left-panel">
            <div>
                <Profile :profile="profile" @load_repo="load_repo"></Profile>
            </div>
            <div>
                repo_url:  {{ format_repo_url }} <br>
                dir_url:  {{ format_dir_url }} <br>
                cur_url:  {{ format_cur_url }} <br>
                file_url:  {{ format_file_url }} <br>
                <AddFileDir @load_repo="load_repo"></AddFileDir>
                <button @click="show_history">History</button>
                <div v-for="repo in target_repo" :key="repo.name">
                    <Directory :item="repo"/>
                </div>
            </div>
        </div>
        <div class="right-panel">
            <FileShow v-if="file_show_flag"></FileShow>
            <History v-if="history_show_flag" :history="target_history"/>
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
  flex: 2;
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