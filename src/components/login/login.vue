

<script>
import loginService from './loginService.js';
import axios from 'axios';
import { APIENDPOINT , getHeader, token } from '../../app.config';
import Vue from 'vue';
import Vuetify from 'vuetify'
Vue.use(Vuetify)

export default {
    template:require('./login.html'),
    data() {
        return {
            loginDetails : {
                login : '',
                password : ''
            },
            toast : {
              snackbar: false,
              color: 'red',
              mode: '',
              timeout: 6000,
              text:''
            },
            el: '#login',
        }
    },
    methods: {
        loginUser:function() {
             const authUser = {}
             var app = this;
            loginService.login(this.loginDetails)
            .then((res) => {
                if(res.data.codeRetour === "0000000000") {
                    authUser.data = res.data.userDto;
                    authUser.data.role_id = "ADMIN";
                    authUser.sessionid = res.headers.cookie;
                    authUser.token = res.headers.authorization;
                    const cookie = document.cookie;
                    app.$store.state.isLoggedIn = true
                    window.localStorage.setItem('lbUser',JSON.stringify(authUser));

                    const axiosCookieJarSupport = require('@3846masa/axios-cookiejar-support');
                    const tough = require('tough-cookie');
                    axiosCookieJarSupport(axios);
                    const cookieJar = new tough.CookieJar();
                    cookieJar.setCookieSync('JSESSIONID=' + authUser.sessionid + '; domain=', APIENDPOINT);


                    if(authUser.data.role_id === 'ADMIN') {
                        console.log("VgfgdfgdfgdfgdfgdfgdfFF");
                     app.$router.push('/admin');
                    }else {
                        console.log("toto");
                      app.$router.push('/resident');
                    }
                }else {
                      console.log('toto');
                      this.toast.text = res.data.messageRetour;
                      this.toast.color='red';
                      this.toast.snackbar=true;
                      app.$store.state.isLoggedIn = false;
                }
            })
            .catch(function (err){
                console.log(err)
            })
        },
        loginAuth:function () {
             var app = this;
            const status =  JSON.parse(window.localStorage.getItem('lbUser'));
            if(status === null || status === undefined) {
                 app.$router.push('/login');
            }else if (status.data.role_id === 'ADMIN') {
              console.log("ADMIN");
               app.$router.push('/admin');
            }else {
              console.log("Resident");
               app.$router.push('/resident');
            }
        }
    },
    created:function() {
        this.loginAuth();
    }
}
</script>

<style lang="scss">
    @import './login.scss';
</style>
