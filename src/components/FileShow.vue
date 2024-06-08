<script setup>
    import {ref,reactive,onMounted} from 'vue';
    const props = defineProps({
        file_path:String,
        file_content:String
    })
    const content = ref("");
    onMounted(() => {
        content.value=props.file_content;
    });
</script>

<template>
    <div>
      <div>
        {{ file_path }}<br>
      </div>
      <textarea v-model="content" class="file-content" @keydown.tab.prevent></textarea>
    </div>
  </template>
  
  <style>
  .file-content {
    width: 80%;
    height: 300px; /* 设置输入框的高度 */
    resize: vertical; /* 允许垂直方向调整大小 */
    border: 1px solid #ccc; /* 添加边框 */
    padding: 10px; /* 添加内边距 */
    font-size: 14px; /* 设置字体大小 */
    line-height: 1.6; /* 设置行高 */
    font-family: Arial, sans-serif; /* 设置字体 */
  }
  </style>