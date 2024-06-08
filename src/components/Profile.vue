<template>
    <div>
      <h1>{{ user.name }}'s Repositories</h1>
      <div v-if="loading">Loading...</div>
      <div v-if="error">{{ error }}</div>
      <div v-if="!loading && !error">
        <Repository
          v-for="(repo,index) in user.repository"
          :key="index"
          :repository="repo"
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
  
//   const fetchUserData = async () => {
//     try {
//       const userId = 'some_user_id'; // Replace with dynamic user ID
//       const userResponse = await axios.get(`https://api.example.com/users/${userId}`);
//       user.value = userResponse.data;
//       const reposResponse = await axios.get(`https://api.example.com/users/${userId}/repos`);
//       repositories.value = reposResponse.data;
//     } catch (err) {
//       error.value = 'Failed to load user data';
//     } finally {
//       loading.value = false;
//     }
//   };
  
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
  