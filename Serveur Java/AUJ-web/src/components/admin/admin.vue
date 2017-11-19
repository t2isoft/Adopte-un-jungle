

<script>
// import axios from 'axios';
// import {APIENDPOINT} from  '../../http-common.js';
import adminService from './adminService.js';

export default {
    template:require('./admin.html'),
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
    @import './admin.scss';
</style>
