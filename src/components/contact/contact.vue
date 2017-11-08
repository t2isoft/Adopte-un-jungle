

<script>
import contactService from './contactService.js';
import axios from 'axios';
import Vue from 'vue'
import Toaster from 'v-toaster'
import 'v-toaster/dist/v-toaster.css'
Vue.use(Toaster)

import { APIENDPOINT , getHeader, token } from '../../app.config';

export default {
  template:require('./contact.html'),
  data() {
    return {
      contactDetails : {
        nomInvocateur : '',
        status : ''
      },
      el: '#contact',
      listContact : null,

    }
  },

  created: function(){
    this.contact()
  },
  methods: {

    contact:function() {
      const axiosCookieJarSupport = require('@3846masa/axios-cookiejar-support');
      const tough = require('tough-cookie');
      axiosCookieJarSupport(axios);
      const cookieJar = new tough.CookieJar();
      const sessionId = JSON.parse(window.localStorage.getItem('lbUser')).sessionid;
      const token = JSON.parse(window.localStorage.getItem('lbUser')).token;

      cookieJar.setCookieSync('JSESSIONID=' + sessionId + '; domain=', APIENDPOINT);

      axios.get(APIENDPOINT + '/contact/mycontact', {
        headers : {
          'Authorization':token
        },
        withCredentials: true
      })
      .then((response) => {
        const data = response.data;
        this.listContact = data.listContact;
        console.log(this.listContact);
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
@import './contact.scss';
</style>
