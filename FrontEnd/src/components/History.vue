<script setup>
    const props = defineProps({
        history:Object
    })
</script>

<template>
    <div>
        <div v-for="item in history" :key="item.time">
            {{item.log}} by {{ item.username }} at {{ item.time }}
        </div>
    </div>
</template>