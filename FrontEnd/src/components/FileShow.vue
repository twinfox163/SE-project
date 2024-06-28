<script setup>
    import { g_data } from '@/store';
    import { onMounted, ref, watch,computed } from 'vue';
    import axios from 'axios';
    const file_content = ref("");
    const props = defineProps({
        config:Object
    })
    onMounted(()=>{
        load_file();
    })

    //监视file_url更新file_content
    watch(
        ()=>g_data.file_url,
        ()=>{
            load_file();
        }
    )
    const load_file=(()=>{
        file_content.value = null;
        if(g_data.file_url){
            const url=import.meta.env.VITE_API_BASE_URL+'/files';
            const params={params:{filename:g_data.file_url}};
            console.log(url,params);
            axios.get(url,params).then(response=>{
                const {status,data}=response;
                console.log(response);
                file_content.value=data;
            })
        }
    })

    const commit_message = ref("");
    const commit=()=>{
        const blob = new Blob([file_content.value], { type: 'text/plain;charset=utf-8' });
        const formData = new FormData();
        let segments = g_data.file_url.split('->');
        const filename = segments[segments.length-1];
        formData.append('file', blob, filename);
        formData.append('path',g_data.dir_url);
        formData.append('mark',commit_message.value);
        const url=import.meta.env.VITE_API_BASE_URL+'/modify';
        console.log(url,formData);
        try{
            axios.post(url,formData).then(response=>{
                const {status,data}=response;
                console.log(response);
                if(data==true){
                    alert('提交成功')
                }
                else{
                    alert('提交失败')
                }
            })
        }catch(error){
            alert('catch error');
        }
    }
    const format_file_url = computed(()=>{
        if(g_data.file_url)
        return g_data.file_url.replace(/->/g,'/');
        else return null;
    })
</script>

<template>
    <div v-if="config.write">
        {{format_file_url}} <br>
        <textarea cols="80" rows="40" v-model="file_content" class="file-content"></textarea>
        <input type="text" v-model="commit_message"><button @click="commit">commit</button><br>
    </div>
    <div v-else>
        {{ format_file_url }} <br>
        <div class="file-content">
            {{ file_content }}
        </div>
    </div>
</template>

<style>
  .file-content {
    width: 80%;
    height: 600px; /* 设置输入框的高度 */
    resize: vertical; /* 允许垂直方向调整大小 */
    border: 1px solid #ccc; /* 添加边框 */
    padding: 10px; /* 添加内边距 */
    font-size: 14px; /* 设置字体大小 */
    line-height: 1.6; /* 设置行高 */
    font-family: Arial, sans-serif; /* 设置字体 */
  }
  </style>