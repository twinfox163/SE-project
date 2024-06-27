<script setup>
    import { g_data } from '@/store';
    import { onMounted, reactive } from 'vue';
    import axios from 'axios';
    import SpaceViewRepo from '@/components/SpaceViewRepo.vue';
    onMounted(()=>{
        load_list();
    })
    const load_list=()=>{
        const url = import.meta.env.VITE_API_BASE_URL+'/follow_list';
        const params = {params:{username:g_data.token}};
        console.log(url,params);
        axios.get(url,params).then(response=>{
            const {status,data}=response;
            console.log(response);
            l_data.follow_list = data;
        }).catch(error=>{
            console.log(error);
            alert('axios error');
        })
    }
    const l_data = reactive({
        follow_list:[],
    })
</script>

<template>
    <div>
        <div>
            <SpaceViewRepo v-for="repo in l_data.follow_list" :key="repo.directory" 
            :item="repo"/>
        </div>
    </div>
</template>