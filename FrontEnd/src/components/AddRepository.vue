<script setup>
    import { g_data } from "@/store";
    import axios from "axios";
    import { onMounted, ref } from "vue";
    const repo_name = ref("");
    const add_repo=(()=>{
        if(repo_name.value.trim()==''){
            g_data.add_repo_flag = false;
            return;
        }
        const url=import.meta.env.VITE_API_BASE_URL+'/new_store';
        const params={params:{username:props.username,name:repo_name.value.trim()}};
        console.log(url,params);
        try{
            axios.get(url,params).then(response=>{
                const {status,data}=response;
                console.log(response);
                if(data=='success'){
                    //添加成功
                    emits('load_repo');
                }else{
                    alert(data);
                }
                g_data.add_repo_flag = false;
            })
        }catch(error){
            g_data.add_repo_flag = false;
            alert("error");
        }
        
    })
    const new_repo_input = ref(null);
    onMounted(()=>{
        new_repo_input.value.focus();
    })
    const handleEnter = () => {
        if (new_repo_input.value) {
            new_repo_input.value.blur(); // 使输入框失去焦点
        }
    };

    const props = defineProps({
        username:String
    })
    const emits = defineEmits(['load_repo'])
</script>

<template>
    <div class="repository-item">
        <h3>
            <input type="text" v-model="repo_name" @keydown.enter="handleEnter" 
                @blur="add_repo" ref="new_repo_input">
        </h3>
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