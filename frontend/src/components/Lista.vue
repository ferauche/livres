<template>
  <table class="container table col-lg-10">
    <thead class="thead">
      <tr>
        <th scope="col">Name</th>
        <th scope="col">Sobrenome</th>
        <th scope="col">CPF</th>
        <th scope="col">Pré-comunidade</th>
      </tr>
    </thead>

    <tbody>
      <tr v-for="consumidor in consumidores" :key="consumidor.cpf">
        <td>{{ consumidor.nome }}</td>
        <td>{{ consumidor.sobrenome }}</td>
        <td>{{ consumidor.cpf }}</td>
        <td>{{ consumidor.precomunidade }}</td>
      </tr>
    </tbody>
  </table>
</template>

<script>
import Consumidores from "../services/consumidores";

export default {
  data() {
    return {
      consumidores: []
    };
  },
  mounted() {
    Consumidores.listar().then(result => {
      this.consumidores = result.data;
    });
  }
};
</script>