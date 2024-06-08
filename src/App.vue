<script setup>
import { RouterLink, RouterView } from 'vue-router'
import HelloWorld from './components/HelloWorld.vue'
</script>

<template>
  <nav class="navbar">
    <router-link to="/">Home</router-link>
    <!-- <router-link to="/signup">Sign up</router-link> -->
    <router-link to="/login">Log in</router-link>
    <!-- <router-link to="/about">About</router-link> -->
  </nav>
  <RouterView />
</template>

<style scoped>
  .navbar {
  position: fixed;
  top: 0;
  left: 0;
  right: 0;
  /* display: flex;
  justify-content: space-between;
  align-items: center;
  height: 50px;
  background-color: #f0f0f0;
  padding: 0 20px; */
  }
</style>


