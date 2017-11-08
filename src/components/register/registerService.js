
import Vue from 'vue';
import { APIENDPOINT , getHeader } from '../../app.config';
import axios from 'axios';
export default {


    register(value, cb) {
        return new Promise(function (resolve, reject) {
              axios.put(APIENDPOINT + '/auth/signin', value)
            .then(function (res) {
                console.log(res);
                resolve(res);
            })
            .catch(function (err) {
                reject(err.response.data)
            })
        });


    }


}
