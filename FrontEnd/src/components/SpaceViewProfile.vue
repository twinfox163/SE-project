<script setup>
    import SpaceViewRepo from '@/components/SpaceViewRepo.vue'
    import { useRouter } from 'vue-router';
    const router = useRouter();
    const props = defineProps({
        profile: Object
    })
    const route_to_repo=(repo_name)=>{
        router.push({name:'Repo',params:{username:props.profile.username,name:repo_name}});
    }
</script>

<template>
    <div>
        <h1>
            {{ profile.username }}'s <br> Repositories
        </h1>
        
        <div>
            <SpaceViewRepo v-for="repo in profile.stores" :key="repo.name" 
            :item="repo" @click="route_to_repo(repo.name)"/>
        </div>
    </div>
</template>

<style scoped>
h1 {
  margin-bottom: 16px;
}
</style>