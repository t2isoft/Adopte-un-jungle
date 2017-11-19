

<script>
import registerService from './registerService.js';
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
        }
    },
    methods: {
        registerUser:function() {
             const authUser = {}
             var app = this;
            registerService.register(this.registerDetails)
            .then(function(res) {
                if(res.data.tRetour.codeRetour === "0000000000") {
                    authUser.data = res.data.userDto;
                    authUser.data.role_id = "ADMIN";
                    authUser.sessionid = res.headers.cookie;
                    authUser.token = res.headers.authorization;
                    app.$store.state.isLoggedIn = true
                    window.localStorage.setItem('lbUser',JSON.stringify(authUser));
                    if(authUser.data.role_id === 'ADMIN') {
                        console.log("VTFF");
                     app.$router.push('/admin');
                    }else {
                        console.log("toto");
                      app.$router.push('/resident');
                    }
                }else {
                      app.$store.state.isLoggedIn = false;
                }
            })
            .catch(function (err){
                console.log(err)
            })
        },
        registerAuth:function () {
             var app = this;
            const status =  JSON.parse(window.localStorage.getItem('lbUser'));
            if(status === null || status === undefined) {
              console.log("VTFF");
                 app.$router.push('/register');
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
        this.registerAuth();
    }
}
</script>

<style lang="scss">
    @import './register.scss';
</style>
