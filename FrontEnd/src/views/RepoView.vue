<script setup>
    import { onMounted,reactive,ref, watch ,computed} from 'vue';
    import { useRoute ,useRouter} from 'vue-router';
    import axios from 'axios';
    import Directory from '@/components/Directory.vue'
    import FileShow from '@/components/FileShow.vue';
    import AddFileDir from '@/components/AddFileDir.vue';
    import History from '@/components/History.vue'
    import {g_data} from '@/store.js'

    const route = useRoute();
    const router = useRouter();
    const username = ref("");
    const repo_name = ref("");
    const l_data=reactive({
        repo_data:null
    })
    //初始
    onMounted(()=>{
        username.value = route.params.username;
        repo_name.value = route.params.name;
        load_repo();
    })
    watch(()=>route.params,()=>{
        username.value = route.params.username;
        repo_name.value = route.params.name;
        load_repo();
    })
    const load_repo=()=>{
        if(username.value.trim!=""){
            const url=import.meta.env.VITE_API_BASE_URL+'/search_repo';
            const params={params:{username:username.value,name:repo_name.value}};
            console.log(url,params);
            axios.get(url,params).then(response=>{
                const {status,data}=response;
                console.log(response);
                if(data!=null){
                    l_data.repo_data=null;
                    l_data.repo_data=data;
                }else{
                    alert("仓管不存在");
                }
            })
        } 
    }
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
    const format_username = computed(()=>{
        if(username.value!="")return username.value;
        else return " ";
    })
    const follow=()=>{
        const url = import.meta.env.VITE_API_BASE_URL+'/follow';
        const formData = new FormData();
        formData.append('username',g_data.token);
        formData.append('store',l_data.repo_data.directory);
        console.log(url,formData);
        axios.post(url,formData).then(response=>{
            const {status,data} = response;
            console.log(response);
            alert(data);
        }).catch(error=>{
            console.log(error);
            alert('axios error');
        })
    }
</script>

<template>
    <div class="home-view">
        <div class="left-panel">
            <div>
                <h1>
                    <router-link :to="{name:'Space',params:{username:format_username}}">{{ username }}</router-link>
                    /{{ repo_name }}<button @click="follow">follow</button>
                </h1>
                dir_url:  {{ format_dir_url }} <br>
                cur_url:  {{ format_cur_url }} <br>
                file_url:  {{ format_file_url }} <br>
                <!-- <AddFileDir @repo_change="load_repo"></AddFileDir> -->
                <button @click="show_history">History</button>
                <Directory v-if="(l_data.repo_data!=null)" :item="l_data.repo_data"/>
            </div>
        </div>
        <div class="right-panel">
            <FileShow v-if="file_show_flag" :config='{write:false}'></FileShow>
            <History v-if="history_show_flag" :history="l_data.repo_data.history"/>
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