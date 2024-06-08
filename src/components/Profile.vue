<template>
    <div>
      <h1>{{ user.username }}'s Repositories</h1>
      <div v-if="loading">Loading...</div>
      <div v-if="error">{{ error }}</div>
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
  </script>
  
  <style scoped>
  h1 {
    margin-bottom: 16px;
  }
  </style>
  