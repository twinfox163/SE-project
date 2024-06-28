<script setup>
    import { computed } from 'vue';

    const props = defineProps({
        history:Array
    })
    const sorted_history  =computed(()=>{
        return props.history.sort((a,b)=>{return new Date(b.time)-new Date(a.time)});
    })
</script>

<template>
    <div>
        <div v-for="item in sorted_history" :key="item.time" class="log-entry">
            <div class="log">{{ item.log }}</div>
            <div class="info">
                <span class="username">by {{ item.username }}</span>
                <span class="time">{{ item.time }}</span>
            </div>
        </div>
    </div>
</template>

<style scoped>
.log-entry {
    margin-bottom: 10px;
    padding: 5px;
    border-bottom: 1px solid #ddd;
}

.log {
    font-weight: bold;
}

.info {
    display: flex;
    justify-content: space-between;
    margin-top: 5px;
}

.username {
    color: #555;
}

.time {
    color: #888;
    font-size: 0.9em;
}
</style>