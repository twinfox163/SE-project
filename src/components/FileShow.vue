<script setup>
    import {ref,reactive} from 'vue';
    const props = defineProps({
        file_path:String,
        file_content:String
    })
</script>

<template>
    <div>
        当前显示文件：{{ file_path }}
        文件内容:<br/>{{ file_content }}
    </div>
</template>