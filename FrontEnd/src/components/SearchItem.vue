<template>
    <div class="search-item" @click="route_to_space">
      <div class="search-item-content">
        <div class="title-container">
          <span class="title-prefix">User</span>
          <h3 class="search-item-title">{{ item.username }}</h3>
        </div>
      </div>
    </div>
  </template>

<script setup>
    import { useRouter } from "vue-router"

    const router = useRouter()
    const props = defineProps({
        item:Object,
    })
    const route_to_space=()=>{
        router.push({name:'Space',params:{username:props.item.username}});
    }
</script>

<style scoped>
.search-item {
  display: flex;
  align-items: center;
  border: 1px solid #ccc;
  padding: 16px;
  margin-bottom: 16px;
  border-radius: 8px;
  background-color: #f9f9f9;
  transition: box-shadow 0.3s, background-color 0.3s;
  cursor: pointer;
}

.search-item-content {
  display: flex;
  align-items: center;
  width: 100%;
}

.title-container {
  display: flex;
  align-items: baseline;
}

.title-prefix {
  font-size: 14px;
  color: #777;
  margin-right: 8px;
}

.search-item-title {
  margin: 0;
  font-size: 24px;
  color: #333;
  font-weight: bold;
}

.search-item:hover {
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
  background-color: #f1f1f1;
}
</style>