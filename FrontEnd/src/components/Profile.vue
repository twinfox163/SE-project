<script setup>
    import Repository from '@/components/Repository.vue'
    import AddRepository from '@/components/AddRepository.vue'
    import { g_data } from '@/store';

    const props = defineProps({
        profile: Object
    })
    const add_repo = (()=>{
        g_data.add_repo_flag=true;
    })
    const emits = defineEmits(['load_repo']);
</script>

<template>
    <div>
        <h1>
            {{ profile.username }}'s <br> Repositories
            <button @click="add_repo">+ repo</button>
        </h1>
        
        <div>
            <AddRepository v-if="g_data.add_repo_flag" :username="profile.username" @load_repo="$emit('load_repo')"/>
            <Repository v-for="repo in profile.repositories" :key="repo.name" :item="repo"/>
        </div>
    </div>
</template>

<style scoped>
h1 {
  margin-bottom: 16px;
}
</style>