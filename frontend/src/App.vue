<template>
  <div>
    <app-header></app-header>
    <app-form></app-form>
  </div>
</template>

<script>
import Header from "./components/Header";
import Form from "./components/Form";

export default {
  components: {
    "app-header": Header,
    "app-form": Form
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
