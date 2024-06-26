<script setup>
    import { g_data } from '@/store';
    import { onMounted, ref, watch } from 'vue';
    import axios from 'axios';
    const file_content = ref("");
    
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
        const url=import.meta.env.VITE_API_BASE_URL+'/modify';
        const params = {params:{file:blob,path:g_data.dir_url,mark:commit_message.value}};
        console.log(url,params);
        try{
            axios.get(url,params).then(response=>{
                const {status,data}=response;
                console.log(response);
                if(data=='success'){
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

</script>

<template>
    <div>
        {{g_data.file_url}} <br>
        <textarea cols="80" rows="40" v-model="file_content" class="file-content"></textarea>
        <input type="text" v-model="commit_message"><button @click="commit">commit</button><br>
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