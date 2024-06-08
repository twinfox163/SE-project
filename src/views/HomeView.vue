<script setup>
  import {ref,onMounted, reactive,computed} from 'vue'
  import Cookies from 'js-cookie'
  import Directory from '@/components/Directory.vue';
  import Profile from '@/components/Profile.vue';
  import axios from  'axios'
  import FileShow from '@/components/FileShow.vue';
import { useRouter } from 'vue-router';
  
  //user信息 包括username,id,库目录数据
  //const router = useRouter;
  const user=reactive({
    id:Cookies.get('id')||0,
    username:Cookies.get('username')||0,
    repositories:[
    {name:"hello",files:["D:->hello->hello->hello.txt","world.c"],children:[{name:"hello1",children:[{name:"world"}]},{name:"hello2"}]},
    {name:"world",files:["",""],children:[{name:"world1"},{name:"world2"}]},
    {name:"zzh",files:["hello.txt"],children:[{name:"zzh1",files:["hello"],children:[]},{name:"zzh2",files:["world"],children:[]}]}]
  });
  //加载库的目录数据
  onMounted(() => {
    //加载数据到user.data

    //没有登录时跳转到login
    const dataUrl = import.meta.env.VITE_API_BASE_URL + "/stores";
    console.log(dataUrl);
    axios.get(dataUrl,{
      params:{
        username:user.username
      }
    })
    .then((response)=>{
      const {status,data}=response;
      user.repositories=data;
    })

    //加载文件数据
    cur_file.value = (Cookies.get('cur_file'));
    const fileUrl = import.meta.env.VITE_API_BASE_URL + "/files/" + `${cur_file.value}`;
    axios.get(fileUrl)
    .then((response)=>{
      const {status,data}=response;
      cur_file_content.value=data;
    })

  });


  //目录显示模块数据,当前的库索引
  const repo_index = ref(Number(Cookies.get('repo_index'))||0);
  //file_show组件数据
  const cur_file = ref("");
  const cur_file_content = ref("hello,world")

</script>

<template>
  <div>
    <!-- <h1>Home</h1> -->
    当前用户id为:{{ Cookies.get('id') }} <br>
    <div>
      <Profile :user="user"></Profile>
    </div>
    <div>
      现在的仓库号是{{ Cookies.get('repo_index') }}
      {{repo_index}}
      <Directory :item="user.repositories[repo_index]" :key="repo_index" />
    </div>
    <!-- 显示文件 -->
    <div>
      <FileShow :file_path="Cookies.get('cur_file')" :file_content="cur_file_content"/> 
    </div>
  </div>
</template>
