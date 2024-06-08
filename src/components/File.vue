<template>
    <div>
        <span>📄</span>
        {{ get_name(item) }}
    </div>
</template>
<script setup>
    const props = defineProps({
        item:String
    });
    const get_name=(path)=>{
        const segments = path.split('->'); // 使用 '->' 分割路径
        return segments[segments.length - 1]; // 返回最后一个元素
    }
</script>