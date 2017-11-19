

<script>
// import axios from 'axios';
// import {APIENDPOINT} from  '../../http-common.js';
import adminService from './contactService.js';

export default {
    template:require('./contact.html'),
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
    @import './contact.scss';
</style>
