

<script>
// import axios from 'axios';
// import {APIENDPOINT} from  '../../http-common.js';
import adminService from './chatService.js';

export default {
    template:require('./chat.html'),
    data() {
        return {
            loginDetails : {
                login : '',
                password : ''
            },


        }
    },
    methods: {

    }
}





</script>

<style lang="scss">
    @import './chat.scss';
</style>
