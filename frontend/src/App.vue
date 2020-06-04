<template>
  <div>
    <app-header></app-header>
    <app-form></app-form>
    <app-lista></app-lista>
    <app-footer></app-footer>
  </div>
</template>

<script>
import Header from "./components/Header";
import Form from "./components/Form";
import Lista from "./components/Lista";
import Footer from "./components/Footer";

export default {
  components: {
    "app-header": Header,
    "app-lista": Lista,
    "app-footer": Footer,
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
