<script setup>
    import {g_data} from '@/store'
    import { computed } from 'vue';
    const props = defineProps({
        item:Object
    })
    const set_repo_url=(()=>{
        g_data.repo_url = props.item.directory;
        g_data.dir_url = props.item.directory;
        g_data.cur_url = props.item.directory;
        g_data.file_url = null;
    })
    const is_selected = computed(()=>{
      if(props.item.directory==g_data.repo_url){
        return true;
      }else{
        return false;
      }
    })
</script>

<template>
    <div class = "repository-item" @click="set_repo_url()">
        <h3 :class="{'target-repo':is_selected}">{{item.name}}</h3>
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
.target-repo {
  background-color: rgb(186, 233, 212);
  color: #fff;
  font-weight: bold;
  padding: 8px;
  border-radius: 4px;
  transition: background-color 0.3s ease, color 0.3s ease;
}
</style>