<template>
  <nav class="navbar navbar-inverse">
    <div class="container-fluid">
      <div class="navbar-header">
        <a class="navbar-brand" href="#">Adopte un jungle</a>
      </div>
      <ul class="nav navbar-nav">
        <li class="active"><a href="/">Home</a></li>
        <li v-on:click="mur()"style="cursor:pointer;" v-if="checkLogin"><a href="/mur">Mur</a></li>
        <li style="cursor:pointer;" v-if="checkLogin"><a href="/contact">Contact</a></li>
        <li style="cursor:pointer;" v-if="checkLogin"><a href="/chat">Chat</a></li>
        <li style="cursor:pointer;" v-if="checkLogin"><a href="/profil">Profil</a></li>
        <li v-else><a href="/register">Register</a></li>
        <li v-on:click="logout()" style="cursor:pointer;" v-if="checkLogin"><a>Logout</a></li>
      </ul>
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
        }
    },
    computed: {
      checkLogin() {
        return this.$store.state.isLoggedIn;
      }
    }
  }

</script>

<style>
</style>
