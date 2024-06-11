<script setup>
import { RouterLink, RouterView } from 'vue-router'
</script>

<template>
  <div class="app-container">
    <nav class="navbar">
      <router-link to="/">Home</router-link>
      <router-link to="/login">Log in</router-link>
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

