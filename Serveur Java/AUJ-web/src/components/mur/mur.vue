

<script>
// import axios from 'axios';
// import {APIENDPOINT} from  '../../http-common.js';
import adminService from './murService.js';
import axios from 'axios';
import { APIENDPOINT , getHeader, token } from '../../app.config';

export default {
    template:require('./mur.html'),
    data() {
        return {
            murDetails : {
                login : '',
                password : ''
            },


        }
    },
    methods: {

      mur:function() {
      const axiosCookieJarSupport = require('@3846masa/axios-cookiejar-support');
      const tough = require('tough-cookie');
      console.log("pute!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
      axiosCookieJarSupport(axios);
      const cookieJar = new tough.CookieJar();
      const sessionId = JSON.parse(window.localStorage.getItem('lbUser')).sessionid;
      const token = JSON.parse(window.localStorage.getItem('lbUser')).token;
      console.log(sessionId);
      console.log(token);
      console.log(cookieJar);
      cookieJar.setCookieSync('JSESSIONID=' + sessionId + '; domain=lesprojets-entrepotos.fr', APIENDPOINT);

      axios.get(APIENDPOINT + '/contact/mycontact', {
        headers: {
        'authorization' : token},
        jar: cookieJar,
        withCredentials: true
      })
      .then((response) => {
        const data = response.data;
        console.log(data.headers.cookie);
      })
      .catch((err) => {
        console.error(err.stack || err);
      });
      }
    }

}

// function getCookie(response) {
//     return response.headers['set-cookie'];
// }
// console.log(getCookie(response));
// axios.get( APIENDPOINT + '/contact/mycontact', {withCredentials: true});

</script>

<style lang="scss">
    @import './mur.scss';
</style>
