<script setup>
    import Repository from '@/components/Repository.vue'

    const props = defineProps({
        profile: Object
    })
</script>

<template>
    <div>
        <h1>
            {{ profile.username }}'s <br> Repositories
            <button>add repo</button>
        </h1>
        
        <div>
            <Repository v-for="repo in profile.repositories" :key="repo.name" :item="repo"/>
        </div>
    </div>
</template>

<style scoped>
h1 {
  margin-bottom: 16px;
}
</style>