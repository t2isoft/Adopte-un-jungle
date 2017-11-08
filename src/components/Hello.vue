<template>
<div class="index">
  <h1>{{msg}}</h1>
  <div class="container">
    <form>
    </form>

  </div>
  </div>
</template>

<script>
export default {
  name: 'index',
  data () {
    return {
      msg: 'Bienvenue sur Adopte un jungle'
    }
  }
}
</script>

<style lang="scss">
  .hello {
    padding:2rem 2rem;
    h1 {
      color:purple;
    }
    h2 {
      margin:2rem 0;
      color:darkgreen;
    }
    p {
      color:darkblue;
    }
  }
</style>
