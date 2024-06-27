<script setup>
    import { onMounted,reactive,ref, watch } from 'vue';
    import { useRoute } from 'vue-router';
    import axios from 'axios';
    import SpaceViewProfile from '@/components/SpaceViewProfile.vue'
    const route = useRoute();
    const username = ref("");
    const l_data=reactive({
        exist:false,
        user_data:{}
    })
    //初始
    onMounted(()=>{
        username.value = route.params.username;
        load_repo();
    })
    watch(()=>route.params.username,()=>{
        username.value = route.params.username;
        load_repo();
    })
    const load_repo=()=>{
        if(username.value.trim!=""){
            const url=import.meta.env.VITE_API_BASE_URL+'/search_user';
            const params={params:{username:username.value}};
            console.log(url,params);
            axios.get(url,params).then(response=>{
                const {status,data}=response;
                console.log(response);
                if(data!=null){
                    l_data.exist=true;
                    l_data.user_data=data;
                }else{
                    l_data.exist=false;
                }
            })
        } 
    }
</script>

<template>
    <div>
        <div v-if="l_data.exist">
            <SpaceViewProfile :profile="l_data.user_data"/>
        </div>
        <div v-else>
            用户{{ username }}不存在
        </div>
    </div>
</template>