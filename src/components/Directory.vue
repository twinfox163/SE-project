<template>
    <div>
      <div @click="toggle">
        <span>📁</span>{{ item.name }}
      </div>
      <div v-if="isOpen" style="margin-left: 20px;">
        <Directory
          v-for="(child, index) in item.children"
          :key="index"
          :item="child"
        />
        <File
          v-for="(file,index) in item.files"
          :key="index"
          :item="file"
        />
      </div>
    </div>
  </template>
  
  <script setup>
  import { ref } from 'vue';
  import File from '@/components/File.vue'
  import Cookies from 'js-cookie';
  const props = defineProps({
    item: Object
  });
  
  const isOpen = ref(false);
  const toggle = () => {
    isOpen.value = !isOpen.value;
    Cookies.set('cur_dir',props.item.directory);
  };
  </script>
  