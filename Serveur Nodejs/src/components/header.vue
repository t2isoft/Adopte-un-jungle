<template>
  <nav class="navbar navbar-default navbar-light navbar-fixed-top  nav-pills nav-stacked" style="background-color: #221194;">
    <div class="container">
      <div class="navbar-header">
        <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
        <a class="navbar-brand active" v-on:click="urlHome()" style="color:orange;">Adopte un jungle</a>
      </div>
      <div id="navbar" class="navbar-collapse collapse">
      <ul class="nav navbar-nav">
        <li v-on:click="mur()"style="cursor:pointer;" v-if="checkLogin"><a v-on:click="urlMur()">Mur</a></li>
        <li style="cursor:pointer;" v-if="checkLogin"><a v-on:click="urlContact()">Contact</a></li>
        <li style="cursor:pointer;" v-if="checkLogin"><a v-on:click="urlSuggestion()">Suggestion</a></li>
        <!--<li style="cursor:pointer;" v-if="checkLogin"><a href="/chat">Chat</a></li>-->
        <li style="cursor:pointer;" v-if="checkLogin"><a v-on:click="urlProfil()">Profil</a></li>
        <li v-else><a v-on:click="urlRegister()">Register</a></li>
        <li v-on:click="logout()" style="cursor:pointer;" v-if="checkLogin"><a>Logout</a></li>
      </ul>
    </div>
    </div>
  </nav>

</template>

<script>
  import VueRouter from 'vue-router';
  export default {
    data() {
      return {
        message:'Header'
      }
    },
    methods: {
        logout:function() {
           var app = this;
         localStorage.removeItem("lbUser");
         app.$router.push({ name: 'login'});
         app.$store.state.isLoggedIn = false;
       },
        urlHome:function() {
          var app = this;
          app.$router.push('/');
       },
        urlRegister:function() {
          var app = this;
          app.$router.push('/register');
       },
        urlMur:function() {
          var app = this;
          app.$router.push('/mur');
       },
        urlContact:function() {
          var app = this;
          app.$router.push('/contact');
       },
        urlSuggestion:function() {
          var app = this;
          app.$router.push('/suggestion');
       },
        urlProfil:function() {
          var app = this;
          app.$router.push('/profil');
       },
    },
    computed: {
      checkLogin() {
        return this.$store.state.isLoggedIn;
      }
    }

  }

</script>

<style>
.navbar-default .navbar-nav>li>a{
  color: white;
}

</style>
