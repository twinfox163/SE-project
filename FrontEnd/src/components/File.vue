<template>
    <div @click = "set_file_url()">
        <span>📄</span>
        {{ file_name }}
    </div>
</template>
<script setup>
    import { computed } from 'vue';
    import { g_data } from '@/store';
    const props = defineProps({
        item:String
    });
    const file_name=computed(()=>{
        let segments = props.item.split('->'); // 使用 '->' 分割路径
        return segments[segments.length-1];
    })
    const set_file_url=()=>{
        g_data.file_url = props.item;
    }
</script>