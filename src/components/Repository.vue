<template>
  <div class="repository-item" @click="set_index()">
    <h3 >{{ repository.name }}</h3>
    <!-- <p>{{ repository.description }}</p> -->
    <!-- /<p>Stars: {{ repository.stars }}</p> -->
    <!-- <a :href="repository.url" target="_blank">View Repository</a> -->
  </div>
</template>

<script setup>
import Cookies from 'js-cookie';
const props = defineProps({
  repository: {
    type: Object,
    required: true
  },
  index:String
});
const {index} = props;
const set_index=()=>{
  Cookies.set('repo_index',index);
  console.log(index);
  window.location.reload()
}
</script>

<style scoped>
.repository-item {
  border: 1px solid #ccc;
  padding: 16px;
  margin-bottom: 16px;
  border-radius: 4px;
}
.repository-item h3 {
  margin: 0 0 8px 0;
}
.repository-item p {
  margin: 4px 0;
}
.repository-item a {
  color: #0366d6;
  text-decoration: none;
}
</style>
