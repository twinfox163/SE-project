<template>
    <div>
      <div @click="toggle">
        <span v-if="item.type === 'folder'">📁</span>
        <span v-else>📄</span>
        {{ item.name }}
      </div>
      <div v-if="item.type === 'folder' && isOpen" style="margin-left: 20px;">
        <Directory
          v-for="(child, index) in item.children"
          :key="index"
          :item="child"
        />
      </div>
    </div>
  </template>
  
  <script setup>
  import { ref } from 'vue';
  const props = defineProps({
    item: Object
  });
  
  const isOpen = ref(false);
  const toggle = () => {
    isOpen.value = !isOpen.value;
  };
  </script>
  