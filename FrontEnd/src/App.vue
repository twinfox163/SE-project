<script setup>
  import { computed, ref } from 'vue';
  import { RouterLink, RouterView } from 'vue-router'
  import { g_data } from './store';
  import Cookies from 'js-cookie';
  import router from './router';
  const logout=()=>{
      Cookies.set('token','');
      g_data.token = '';
      router.push('/login');
  }
  const search_item = ref("");
  const search_link = computed(()=>{
    if(search_item.value!=0)return search_item.value;
    else return " ";
  })
</script>

<template>
  <div class="app-container">
    <nav class="navbar">
      <router-link to="/">Home</router-link>
      <router-link to="/following">Following</router-link>
      <span>
        <input type="text" v-model="search_item">
        <router-link :to="{name:'Space',params:{username:search_link}}">Search</router-link>
      </span>
      <router-link v-if="g_data.token==''" to="/login">Log in</router-link>
      <button v-else @click="logout()">Log out</button>
    </nav>
    <div class="router-container">
      <RouterView />
    </div>
  </div>
</template>

<style scoped>
  .navbar {
  position: fixed;
  top: 0;
  left: 0;
  right: 0;
  display: flex;
  justify-content: space-between;
  align-items: center;
  height: 50px;
  background-color: #f0f0f0;
  padding: 0 20px;
  }

  .router-container{
    display: flex;
    margin-top: 60px;
    height: calc(100vh - 60px);
  }
</style>

