<script setup>
    import { g_data } from '@/store';
    import { computed } from 'vue';
    import {useRouter} from 'vue-router'
    const router = useRouter();
    const props =defineProps({
        item:Object
    })
    const repo_data = computed(()=>{
      let segments = props.item.directory.split('->');
      return {
        username:segments[0],
        repo_name:segments[1]
      }
    })
    const route_to_repo=()=>{
        g_data.repo_url = props.item.directory;
        g_data.dir_url = props.item.directory;
        g_data.cur_url = props.item.directory;
        g_data.file_url = null;
        g_data.cur_show = 'dir';
        router.push({name:'Repo',params:{username:repo_data.value.username,name:repo_data.value.repo_name}});
    }
</script>

<template>
    <div class = "repository-item" @click="route_to_repo">
        <h3>{{repo_data.username}}/{{ repo_data.repo_name }}</h3>
    </div>
</template>

<style scoped>
.repository-item {
  border: 1px solid #ccc;
  padding: 16px;
  margin-bottom: 16px;
  border-radius: 4px;
}
.repository-item h3 {
  margin: 0 0 3px 0;
}
.repository-item p {
  margin: 4px 0;
}
.repository-item a {
  color: #0366d6;
  text-decoration: none;
}
</style>