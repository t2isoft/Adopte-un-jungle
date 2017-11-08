

<script>
import profilService from './profilService.js';
import axios from 'axios';
import Vue from 'vue'
import Toaster from 'v-toaster'
import 'v-toaster/dist/v-toaster.css'
Vue.use(Toaster)

import { APIENDPOINT , getHeader, token } from '../../app.config';

export default {
  template:require('./profil.html'),
  data() {
    return {
      profilDetails : {
        username : '',
        password : '',
        role : '',
        nomInvocateur : '',
        email : '',
        password : ''
      },
      el: '#profil',
      posts : null,

    }
  },

  created: function(){
    this.profil()
  },
  methods: {

    profil:function() {
      const axiosCookieJarSupport = require('@3846masa/axios-cookiejar-support');
      const tough = require('tough-cookie');
      axiosCookieJarSupport(axios);
      const cookieJar = new tough.CookieJar();
      const sessionId = JSON.parse(window.localStorage.getItem('lbUser')).sessionid;
      const token = JSON.parse(window.localStorage.getItem('lbUser')).token;

      cookieJar.setCookieSync('JSESSIONID=' + sessionId + '; domain=', APIENDPOINT);

      axios.get(APIENDPOINT + '/user/watchProfil', {
        headers : {
          'Authorization':token
        },
        withCredentials: true
      })
      .then((response) => {
        const data = response.data;
      })
      .catch((err) => {
        console.error(err.stack || err);
      });
    },

    newPublication:function() {
      const axiosCookieJarSupport = require('@3846masa/axios-cookiejar-support');
      const tough = require('tough-cookie');
      axiosCookieJarSupport(axios);
      const cookieJar = new tough.CookieJar();
      const sessionId = JSON.parse(window.localStorage.getItem('lbUser')).sessionid;
      const token = JSON.parse(window.localStorage.getItem('lbUser')).token;

      cookieJar.setCookieSync('JSESSIONID=' + sessionId + '; domain=', APIENDPOINT);

      axios({
        method:'put',
        url:'https://lesprojets-entrepotos.fr/AUJ-api/post/newPost',
        data: {"message": this.adminDetails.message},
        headers : {
                 'Authorization':token
                 },
        withCredentials: true
      })
      .then((response) => {
        const data = response.data;
        this.posts = data.posts;
        console.log(response);
      })
      .catch((err) => {
        console.error(err.stack || err);
      });
    }
  }
}

</script>

<style lang="scss">
@import './profil.scss';
</style>
