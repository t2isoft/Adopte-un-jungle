
import Vue from 'vue';
import { APIENDPOINT , getHeader } from '../../app.config';
import axios from 'axios';
export default {


    login(value, cb) {
        return new Promise(function (resolve, reject) {
              axios.post(APIENDPOINT + '/auth/login', value, {withCredentials: true})
            .then(function (res) {
                console.log(res);
                resolve(res);
            })
            .catch(function (err) {
                reject(err)
            })
        });


    }


}
