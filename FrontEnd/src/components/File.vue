<template>
    <div @click="set_file_url()" :class="{'target-file':is_selected}">
        <span>📄</span>
        {{ file_name }}
    </div>
</template>
<script setup>
    import { computed } from 'vue';
    import { g_data } from '@/store';
    const props = defineProps({
        item:String
    });
    const file_name=computed(()=>{
        let segments = props.item.split('->'); // 使用 '->' 分割路径
        return segments[segments.length-1];
    })
    const set_file_url=()=>{
        g_data.file_url = props.item;
        g_data.cur_url = props.item;
        g_data.dir_url = get_dir_url(props.item);
    }
    const get_dir_url=((file_url)=>{
        const parts = file_url.split('->');
        if(parts.length>1){
            parts.pop();
        }
        return parts.join('->');
    })
    const is_selected=computed(()=>{
        return (g_data.cur_url == props.item);
    })
</script>

<style scoped>
.target-file {
  background-color: rgb(186, 233, 212);
  cursor: pointer;
  /* padding: 5px; */
  border-radius: 5px;
  color: white;
}
</style>