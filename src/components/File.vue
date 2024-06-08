<template>
    <div>
        <span>📄</span>
        {{ item }}
    </div>
</template>
<script setup>
    const props = defineProps({
        item:String
    });
</script>