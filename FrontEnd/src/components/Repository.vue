<script setup>
    import {g_data} from '@/store'
    const props = defineProps({
        item:Object
    })
    const set_repo_url=(()=>{
        g_data.repo_url = props.item.directory;
        g_data.dir_url = props.item.directory;
        g_data.cur_url = props.item.directory;
        g_data.file_url = null;
    })
</script>

<template>
    <div class = "repository-item" @click="set_repo_url()">
        <h3>{{item.name}}</h3>
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