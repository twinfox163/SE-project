<script setup>
    const props =defineProps({
        item:Object
    })
</script>

<template>
    <div class = "repository-item">
        <h3>{{item.name}}</h3>
    </div>
</template>

<style scoped>
.repository-item {
  border: 1px solid #ccc;
  padding: 16px;
  margin-bottom: 16px;
  border-radius: 4px;
}
.repository-item h3 {
  margin: 0 0 3px 0;
}
.repository-item p {
  margin: 4px 0;
}
.repository-item a {
  color: #0366d6;
  text-decoration: none;
}
</style>