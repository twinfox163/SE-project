<template>
    <div>
      <h1>{{ user.username }}'s Repositories</h1>
      <div v-if="loading">Loading...</div>
      <div v-if="error">{{ error }}</div>
      <!-- 增加新的库 -->
      new repo:<input type="text" v-model="new_repo" @keydown.enter="set_new_repo">
      <div v-if="!loading && !error">
        <Repository
          v-for="(repo,index) in user.repositories"
          :key="index"
          :repository="repo"
          :index="String(index)"
        />
      </div>
    </div>
</template>
  
  <script setup>
  import { ref, onMounted } from 'vue';
  import axios from 'axios';
  import Repository from '../components/Repository.vue';
  
  //const user = ref({});
  //const repositories = ref([]);
  const loading = ref(false);
  const error = ref(null);
  const props = defineProps({
    user: {
        type :Object,
        required: true
    }
    //repositories: Array
  });
  const {user} = props;
  const show=()=>{
    console.log(user.id);
  };
  
  onMounted(() => {
    //console.log(this.user);
    //fetchUserData();
  });

  //增加库的功能:
  const new_repo=ref("");
  const set_new_repo=()=>{
    const Url = import.meta.env.VITE_API_BASE_URL + "/new_store";
    console.log(Url);
    console.log(new_repo.value)
    axios.get(Url,{
      params:{
        username:user.username,
        name:new_repo.value
      }
    })
    .then((response)=>{
      const {status,data}=response;
      alert(data);
      system.location.reload();
    })
  }
  </script>
  
  <style scoped>
  h1 {
    margin-bottom: 16px;
  }
  </style>
  