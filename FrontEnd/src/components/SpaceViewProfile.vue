<script setup>
    import SpaceViewRepo from '@/components/SpaceViewRepo.vue'
    import { g_data } from '@/store';
    import { useRouter } from 'vue-router';

    const router = useRouter();
    const props = defineProps({
        profile: Object
    })
    const route_to_repo=(repo)=>{
        g_data.repo_url = repo.directory;
        g_data.dir_url = repo.directory;
        g_data.cur_url = repo.directory;
        g_data.file_url = null;
        g_data.cur_show = 'dir';
        router.push({name:'Repo',params:{username:props.profile.username,name:repo.name}});
    }
</script>

<template>
    <div>
        <h1>
            {{ profile.username }}'s <br> Repositories
        </h1>
        
        <div>
            <SpaceViewRepo v-for="repo in profile.stores" :key="repo.name" 
            :item="repo" @click="route_to_repo(repo)"/>
        </div>
    </div>
</template>

<style scoped>
h1 {
  margin-bottom: 16px;
}
</style>