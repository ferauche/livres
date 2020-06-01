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

export default {
  components: {
    "app-header": Header,
    "app-lista": Lista
    // "app-form": Form
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
