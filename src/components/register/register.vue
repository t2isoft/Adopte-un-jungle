

<script>
import registerService from './registerService.js';
import axios from 'axios';
import Vue from 'vue';
import Vuetify from 'vuetify'
Vue.use(Vuetify)

import { APIENDPOINT , getHeader, token } from '../../app.config';

export default {
    template:require('./register.html'),
    data() {
        return {
          registerDetails : {
                username : '',
                password : '',
                role : '',
                nomInvocateur : '',
                email : '',
                password : ''
            },
            toast : {
              snackbar: false,
              color: 'red',
              mode: '',
              timeout: 6000,
              text:''
            },
            el: '#register',
        }
    },

    methods: {
        registerUser: function() {
           const authUser = {}
           var app = this;
           registerService.register(this.registerDetails)
            .then((res) => {
              console.log(res);
                if(res.data.tRetour.codeRetour === "0000000000") {
                  const data = response.data;
                  console.log("Compte créé");
                  this.toast.color='green';
                  this.toast.text='Compte créé';
                  this.toast.snackbar=true;
                } else {
                  console.log(res.data.tRetour.messageRetour);
                  this.toast.text = res.data.tRetour.messageRetour;
                  this.toast.color='red';
                  this.toast.snackbar=true;
                }
            })
            .catch(function (err){
                console.log(err);
            })
        },
        registerAuth:function () {
             var app = this;
            const status =  JSON.parse(window.localStorage.getItem('lbUser'));
            if(status === null || status === undefined) {
                 app.$router.push('/register');
            }else if (status.data.role_id === 'ADMIN') {
              console.log("ADMIN");
               app.$router.push('/admin');
            }else {
              console.log("Resident");
               app.$router.push('/resident');
            }
        }
    }
}


</script>

<style lang="scss">
    @import './register.scss';
</style>
