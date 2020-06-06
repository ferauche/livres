<template>
  <div class="container col-lg-10 mt-3">
    <div class="card livres-segundary text-center card-form">
      <div class="card-body text-light">
        <h3>Consumidor</h3>
        <p>Formulário de cadastro de consumidor</p>
        <form @submit.prevent="salvar">
          <div class="form-group">
            <input
              type="text"
              name="nome"
              id="nome"
              class="form-control form-control-lg"
              placeholder="nome"
              v-model="consumidor.nome"
            />

            <input
              type="text"
              name="sobrenome"
              id="sobrenome"
              class="form-control form-control-lg"
              placeholder="sobrenome"
              v-model="consumidor.sobrenome"
            />

            <input
              type="text"
              name="cpf"
              id="cpf"
              class="form-control form-control-lg"
              placeholder="cpf"
              v-model="consumidor.cpf"
            />

            <input
              type="text"
              name="senha"
              id="senha"
              class="form-control form-control-lg"
              placeholder="senha"
              v-model="consumidor.senha"
            />

            <input
              type="text"
              name="precomunidade"
              id="precomunidade"
              class="form-control form-control-lg"
              placeholder="precomunidade"
              v-model="consumidor.precomunidade"
            />
          </div>
          <input
            type="submit"
            id="btnEnviar"
            name="btnEnviar"
            value="Enviar"
            class="btn btn-block btn-outline-light"
          />
        </form>
      </div>
      <p>{{ consumidor.nome }}</p>
      <p>{{ consumidor.sobrenome }}</p>
      <p>{{ consumidor.cpf }}</p>
      <p>{{ consumidor.senha }}</p>
      <p>{{ consumidor.precomunidade }}</p>
    </div>
  </div>
</template>

<script>
import Consumidores from "../services/consumidores";

export default {
  data() {
    return {
      consumidor: {
        nome: "",
        sobrenome: "",
        cpf: "",
        senha: "",
        precomunidade: null
      }
    };
  },
  methods: {
    salvar: function() {
      Consumidores.salvar(this.consumidor)
        .then(() => {
          alert("Adicionado com sucesso");
        })
        .catch(() => {
          alert("Ocorreu um erro inesperado");
        });
    }
  }
};
</script>

<style>
</style>