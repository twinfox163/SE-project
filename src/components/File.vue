<template>
    <div @click = "set_cur_file(item)">
        <span>📄</span>
        {{ get_name(item) }}
    </div>
</template>
<script setup>
    import Cookies from 'js-cookie';
    const props = defineProps({
        item:String
    });
    const get_name=(path)=>{
        const segments = path.split('->'); // 使用 '->' 分割路径
        return segments[segments.length - 1]; // 返回最后一个元素
    }
    //向父组件提供信息
    const set_cur_file=(path)=>{
        Cookies.set('cur_file',path);
        window.location.reload();
    }
    
</script>