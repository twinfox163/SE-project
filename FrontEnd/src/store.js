import {reactive,computed} from 'vue'

export const g_data = reactive({
    repo_url:null,
    dir_url:null,
    file_url:null,
    cur_url:null,
    add_repo_flag:false,
    token:'',
    cur_show:'',
})
