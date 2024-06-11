<template>
    <div>
      <div @click="toggle" :class = "{'target-dir':is_selected}">
        <span v-if="isOpen">▼ 📂</span>
        <span v-else>▶ 📁</span> 
        {{ item.name }}
      </div>
      <div v-if="isOpen" style="margin-left: 20px;">
        <Directory
          v-for="(child, index) in item.children"
          :key="index"
          :item="child"
        />
        <div style="margin-left: 17px;">
        <File
          v-for="(file,index) in item.files"
          :key="index"
          :item="file"
        />
        </div>
      </div>
    </div>
  </template>
  
  <script setup>
  import { computed, ref } from 'vue';
  import File from '@/components/File.vue'
  import { g_data } from '@/store';
  const props = defineProps({
    item: Object
  });
  const isOpen = ref(false);
  const toggle = () => {
    isOpen.value = !isOpen.value;
    g_data.dir_url = props.item.directory;
  };
  const is_selected = computed(()=>{
    return (g_data.dir_url == props.item.directory);
  })
  </script>

<style scoped>
.target-dir {
  background-color: rgb(186, 233, 212);
  cursor: pointer;
  padding: 5px;
  border-radius: 5px;
  color: white;
}
</style>
  