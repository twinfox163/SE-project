<script setup>
    import { g_data } from '@/store';
    import { onMounted, ref } from 'vue';
    const props = defineProps({
        file_content:String
    })
    const content = ref("");
    onMounted(()=>{
        content.value = props.file_content;
    })
</script>

<template>
    <div>
        {{g_data.file_url}} <br>
        <textarea cols="70" rows="50" v-model="content"></textarea>
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