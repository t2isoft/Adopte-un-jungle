

<script>
// import axios from 'axios';
// import {APIENDPOINT} from  '../../http-common.js';
import adminService from './profilService.js';

export default {
    template:require('./profil.html'),
    data() {
        return {
            profilDetails : {
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
    @import './profil.scss';
</style>
