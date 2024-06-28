<script setup>
    import SearchBar from '@/components/SearchBar.vue';
    import SearchItem from '@/components/SearchItem.vue';
    import axios from 'axios';
    import { onMounted, watch, reactive, computed } from 'vue';
    import { useRoute } from 'vue-router';
    const route = useRoute();
    const url_data = reactive({
        query:"",
        type:""
    })
    const l_data = reactive({
        user_data:null,
        search_data:[],
    })
    const exist = computed(()=>{
        if(l_data.search_data.length>0)return true;
        else return false;
    })
    onMounted(()=>{
        url_data.query = route.query.query;
        url_data.type = route.query.type;    
        load_data();
    })
    watch(()=>route.query,()=>{
        url_data.query = route.query.query;
        url_data.type = route.query.type;    
        load_data();
    })
    const load_data=()=>{
        if(url_data.type=='username'&&url_data.query.trim()!=''){
            const url=import.meta.env.VITE_API_BASE_URL+'/search_user';
            const params={params:{username:url_data.query}};
            console.log(url,params);
            axios.get(url,params).then(response=>{
                const {status,data}=response;
                console.log(response);
                if(data){
                    l_data.user_data = data;
                    fun1();
                }else{
                    l_data.search_data = [];
                }
            })
        }
    }
    const fun1 = ()=>{
        const new_data = {type:'user',username:l_data.user_data.username};
        l_data.search_data[0]=new_data;
    }
    const handle_search = ()=>{
        load_data();
    }
</script>

<template>
    <div>
        <SearchBar @search="handle_search"/>
        <div v-if="exist">
            <SearchItem v-for="item in l_data.search_data" :key="item" :item="item"/>
        </div>
        <div v-else>
            什么都没有找到
        </div>
    </div>
</template>