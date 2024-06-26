<script setup>
    import { computed, ref } from "vue";
    import { g_data } from "../store";
    import axios from "axios";
    const format_dir_url = computed(()=>{
        if(g_data.dir_url)
        return g_data.dir_url.replace(/->/g,'/');
        else return null;
    })
    const add_dir_flag = ref(false);
    const add_flag = ref(false);
    const add_name = ref("");
    const add_dir = ()=>{
        add_name.value = "";
        add_flag.value = true;
        add_dir_flag.value = true;
        add_input.value.focus();
    }
    const add_file = ()=>{
        add_name.value = "";
        add_flag.value = true;
        add_dir_flag.value = false;
        add_input.value.focus();
    }
    const add_input = ref(null);
    const handleEnter = () => {
        if (add_input.value) {
            add_input.value.blur(); // 使输入框失去焦点
        }
    };
    const input_blur = ()=>{
        
        if(add_name.value.trim()==''){
            add_flag.value = false;
            return;
        }
        if(add_dir_flag.value){
            const url=import.meta.env.VITE_API_BASE_URL+'/new_dir';
            const params={params:{path:g_data.dir_url,dirname:add_name.value.trim()}};
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
                add_flag.value = false;
            })
            }catch(error){
                add_flag.value = false;
                alert("error");
            }
        }else{
            //添加文件
        }
    }
    const emits = defineEmits(['load_repo']);

    const file = ref(null);
    const onFileChange = ()=>{
        file.value = event.target.files[0];
    }
    const uploadFile = ()=>{
        if(file.value){
            //添加文件
            const url=import.meta.env.VITE_API_BASE_URL+'/upload';
            const formData = new FormData();
            formData.append('file',file);
            formData.append('path',g_data.dir_url);
            console.log(url,formData);
            try{
            axios.post(url,formData).then(response=>{
                const {status,data}=response;
                console.log(response);
                if(data=='success'){
                    //添加成功
                    emits('load_repo');
                }else{
                    alert(data);
                }
            })
            }catch(error){
                alert("error");
            }
        }
    }
</script>

<template>
    <div>
        <button @click="add_file">+ file</button>
        <button @click="add_dir">+ dir</button> <br>
        <div v-show="add_flag">
            <span v-if="add_dir_flag">new dir:</span>
            <span v-else>new file</span>
            {{ format_dir_url }}
            <input type="text" v-model="add_name" ref="add_input"
                @blur="input_blur" @keydown.enter="handleEnter">
            <br>
        </div>
        <input type="file" @change="onFileChange"/>
        <button @click="uploadFile">Upload</button>
    </div>
</template>