<template>
  <div>
    <app-header></app-header>
    <!-- <app-form></app-form> -->
    <app-lista></app-lista>
  </div>
</template>

<script>
import Header from "./components/Header";
// import Form from "./components/Form";
import Lista from "./components/Lista";
import Users from "./services/users";

export default {
  components: {
    "app-header": Header,
    "app-lista": Lista
    // "app-form": Form
  },
  data() {
    return {
      users: []
    };
  },
  mounted() {
    Users.listar().then(result => {
      console.log(result.data);
      this.users = result.data;
    });
  }
};
</script>

<style>
.livres-primary {
  background: #41b1e0;
}

.livres-segundary {
  background: #c27351;
}
</style>
