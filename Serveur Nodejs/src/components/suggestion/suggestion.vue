

<script>
import suggestionService from './suggestionService.js';
import axios from 'axios';
import Vue from 'vue'
import Vuetify from 'vuetify'
Vue.use(Vuetify)

import { APIENDPOINT , getHeader, token } from '../../app.config';

export default {
  template:require('./suggestion.html'),
  data() {
    return {
      suggestionDetails : {
        nomInvocateur : '',
        status : ''
      },
      toast : {
        snackbar: false,
        color: 'red',
        mode: '',
        timeout: 6000,
        text:''
      },
      el: '#suggestion',
      listContact : null,

    }
  },

  created: function(){
    this.suggestion()
  },
  methods: {

    suggestion:function() {
      const axiosCookieJarSupport = require('@3846masa/axios-cookiejar-support');
      const tough = require('tough-cookie');
      axiosCookieJarSupport(axios);
      const cookieJar = new tough.CookieJar();
      const sessionId = JSON.parse(window.localStorage.getItem('lbUser')).sessionid;
      const token = JSON.parse(window.localStorage.getItem('lbUser')).token;
      var app = this;
      cookieJar.setCookieSync('JSESSIONID=' + sessionId + '; domain=', APIENDPOINT);

      axios.get(APIENDPOINT + '/contact/suggestions', {
        headers : {
          'Authorization':token
        },
        withCredentials: true
      })
      .then((response) => {
        console.log(response);
        if(response.data.tRetour.codeRetour === "0000000000") {
          const data = response.data;
          this.listContact = data.listContact;
          this.toast.text = response.data.tRetour.messageRetour;
          this.toast.color='green';
          this.toast.snackbar=true;
          console.log("valide");
          } else {
          this.toast.text = response.data.tRetour.messageRetour;
          this.toast.color='red';
          this.toast.snackbar=true;
          console.log("pas valide");
          }
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
@import './suggestion.scss';
</style>
