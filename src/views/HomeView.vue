<script setup>
  import {ref,onMounted, reactive} from 'vue'
  import Cookies from 'js-cookie'
  import Directory from '@/components/Directory.vue';
  import Profile from '@/components/Profile.vue';

  const m_id = ref(Number(Cookies.get('id')) || 0);
  const directoryStructure = {
    name: 'root',
    type: 'folder',
    children: [
      {
        name: 'folder1',
        type: 'folder',
        children: [
          {
            name: 'file1.txt',
            type: 'file'
          },
          {
            name: 'file2.txt',
            type: 'file'
          }
        ]
      },
      {
        name: 'file3.txt',
        type: 'file'
      }
    ]
  };
  //user信息
  const user=reactive({
    id:"112563",
    name:"otofly",
    repository:["hello-project","todo-project"]
  });
  onMounted(() => {

    //console.log("hello,world");
  });
</script>

<template>
  <div>
    <!-- <h1>Home</h1> -->
    当前用户id为:{{ (Cookies.get('id')|| 0) }} <br>
    <div>
      <Profile :user="user"></Profile>
    </div>
    <div>
      <Directory :item="directoryStructure" />
    </div>
    <!-- <div>
      <h1>Directory Structure</h1>
      <Directory :item="directoryStructure" />
    </div> -->

    <!-- <button @click="login">get id</button> -->
    <!-- 当前id为:{{ id }}; -->
    <!-- <TheWelcome /> -->
  </div>
</template>
