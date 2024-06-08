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
        当前显示文件：{{ file_path }}<br>
        文件内容:<br/><input type="text" v-model="content">
    </div>
</template>