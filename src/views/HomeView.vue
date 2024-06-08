<script setup>
  import {ref,onMounted, reactive,computed} from 'vue'
  import Cookies from 'js-cookie'
  import Directory from '@/components/Directory.vue';
  import Profile from '@/components/Profile.vue';
  import axios from  'axios'

  //const m_id = ref(Number(Cookies.get('id')) || 0);
  //const repo_index = ref(Cookies.get('repo-index'));
  const repo_index = ref(Number(Cookies.get('repo_index'))||0);
  //user信息
  const user=reactive({
    id:Cookies.get('id')||0,
    username:Cookies.get('username')||0,
    repositories:[
    {name:"hello",files:["D:->hello->hello->hello.txt","world.c"],children:[{name:"hello1",children:[{name:"world"}]},{name:"hello2"}]},
    {name:"world",files:["",""],children:[{name:"world1"},{name:"world2"}]},
    {name:"zzh",files:["hello.txt"],children:[{name:"zzh1",files:["hello"],children:[]},{name:"zzh2",files:["world"],children:[]}]}]
  });
  onMounted(() => {
    //加载数据到user.data
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
  });
</script>

<template>
  <div>
    <!-- <h1>Home</h1> -->
    当前用户id为:{{ Cookies.get('id') }} <br>
    {{ m_id }}
    <div>
      <Profile :user="user"></Profile>
    </div>
    <div>
      现在的仓库号是{{ Cookies.get('repo_index') }}
      {{repo_index}}
      <Directory :item="user.repositories[repo_index]" :key="repo_index" />
    </div>
    <!-- 显示文件 -->
  </div>
</template>
