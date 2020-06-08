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

    <!-- Modal -->
    <div
      class="modal fade"
      id="deletarModal"
      tabindex="-1"
      role="dialog"
      aria-labelledby="deletarModal"
      aria-hidden="true"
    >
      <div class="modal-dialog" role="document">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title" id="tituloModal">Excluir</h5>
            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
              <span aria-hidden="true">&times;</span>
            </button>
          </div>
          <div class="modal-body">Tem certeza que deseja excluir este consumidor?</div>
          <div class="modal-footer">
            <button type="button" class="btn" data-dismiss="modal">Não</button>
            <button type="button" class="btn btn-danger">Sim</button>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
// import Consumidores from "../services/consumidores";

export default {
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
