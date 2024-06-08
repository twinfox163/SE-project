<script setup>
import { RouterLink, RouterView } from 'vue-router'
import HelloWorld from './components/HelloWorld.vue'
</script>

<template>

  <!-- <header>
    <img alt="Vue logo" class="logo" src="@/assets/logo.svg" width="125" height="125" />

    <div class="wrapper">
      <HelloWorld msg="You did it!" />

      <nav>
        <RouterLink to="/">Home</RouterLink>
        <RouterLink to="/about">About</RouterLink>
      </nav>
    </div>
  </header> -->
  <header>
  <nav>
    <router-link to="/">Home</router-link>
    <router-link to="/signup">Sign up</router-link>
    <router-link to="/login">Log in</router-link>
    <router-link to="/about">About</router-link>
    <br>
  </nav>
  </header>
  <RouterView />
</template>

