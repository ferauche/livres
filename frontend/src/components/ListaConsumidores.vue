<template>
  <div>
    <table class="container table">
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
          <td class="row justify-content-end">
            <router-link class="btn btn-outline-primary mr-3" to="consumidores/cadastro">
              <i class="fas fa-pencil-alt"></i>
            </router-link>
            <a
              class="btn btn-outline-danger"
              type="button"
              data-toggle="modal"
              data-target="#deletarModal"
            >
              <i class="fas fa-trash-alt"></i>
            </a>
          </td>
        </tr>
      </tbody>
    </table>
    <router-link class="btn btn-outline-primary mr-3" to="consumidores/cadastro">
      <i class="fas fa-plus"></i>
    </router-link>

    <com-modal-delete
      mensagem="Deseja excluir este consumidor?"
      v-on:confirmar="ExcluirConsumidor"
    />
  </div>
</template>

<script>
// import Consumidores from "../services/consumidores";
import modalDelete from "./modalDelete";

export default {
  components: {
    "com-modal-delete": modalDelete
  },
  data() {
    return {
      consumidores: [
        { nome: "teste", sobrenome: "teste", cpf: "teste", precomunidade: 1 },
        { nome: "teste", sobrenome: "teste", cpf: "teste", precomunidade: 1 },
        { nome: "teste", sobrenome: "teste", cpf: "teste", precomunidade: 1 },
        { nome: "teste", sobrenome: "teste", cpf: "teste", precomunidade: 1 }
      ]
    };
  },
  methods: {
    ExcluirConsumidor: function() {
      this.$toaster.error("Consumidor Removido com sucesso");
    }
  },
  created() {},
  mounted() {
    // Consumidores.listar()
    //   .then(result => {
    //     this.consumidores = result.data;
    //   })
    //   .catch(() => {
    //     this.$toaster.error("Erro ao carregar lista");
    //   });
  }
};
</script>
