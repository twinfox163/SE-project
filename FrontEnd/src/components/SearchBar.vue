<template>
    <div class="search-bar">
        <input type="text" v-model="search_query"
            @keydown.enter="handleSearch" placeholder="Search..." />
        <button @click="handleSearch">Search</button>
    </div>
</template>
<script setup>
    import { ref } from 'vue';
    import { useRouter,useRoute } from 'vue-router';
    const router = useRouter();
    const route = useRoute();
    const search_query = ref("");
    const handleSearch = ()=>{
        if(route.query.query!=search_query.value){
            router.push({name:'Search',query:{query:search_query.value,type:'username'}});
        }
        else{
            emits('search');
        }
    }
    const emits = defineEmits(['search']);
</script>

<style scoped>
.search-bar {
  display: flex;
  align-items: center;
}

.search-bar input {
  flex: 1;
  padding: 8px;
  margin-right: 8px;
}

.search-bar button {
  padding: 8px 16px;
}
</style>