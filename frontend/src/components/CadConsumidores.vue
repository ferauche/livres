<template>
  <div class="row">
    <div class="col-md-6 col-sm-12 offset-md-3">
      <div class="card">
        <div class="card-header">
          Cadastro de Consumidores
        </div>
        <div class="card-body">
          <form id="formCadConsumidores">
            <div class="md-form">
              <input
                autocomplete="off"
                type="text"
                id="nome"
                class="form-control"
                v-model.trim="nome"
              />
              <label for="nome">Nome</label>
            </div>
            <div class="md-form">
              <input
                autocomplete="off"
                type="text"
                id="sobrenome"
                class="form-control"
                v-model.trim="sobrenome"
              />
              <label for="sobrenome">Sobrenome</label>
            </div>
            <div class="md-form">
              <input
                autocomplete="off"
                type="text"
                id="cpf"
                class="form-control"
                v-model.trim="cpf"
              />
              <label for="cpf">CPF</label>
            </div>
            <div class="md-form">
              <input
                autocomplete="off"
                type="password"
                id="senha"
                class="form-control"
                v-model.trim="senha"
              />
              <label for="senha">Senha</label>
            </div>
            <div class="pt-1"></div>
            <div class="select">
              <select class="select-text" required v-model="precomunidade">
                <option disabled selected></option>
                <option
                  v-for="option in precomunidades"
                  :key="option.id"
                  v-bind:value="option.id"
                >
                  {{ option.value }} </option
                >>
              </select>
              <span class="select-highlight"></span>
              <span class="select-bar"></span>
              <label class="select-label">Pré-comunidade:</label>
            </div>
            <div class="pt-3"></div>
            <div class="row">
              <div class="col text-right">
                <button
                  type="button"
                  class="btn btn-primary pull-right"
                  @click="cadastrar()"
                >
                  <i class="fa fa-floppy-o"></i> Cadastrar
                </button>
              </div>
            </div>
          </form>
        </div>
      </div>
    </div>
  </div>


</template>

<script>
export default {
  data() {
    return {
      nome: "",
      sobrenome: "",
      cpf: "",
      senha: "",
      precomunidade: "",
      precomunidades: [],
    };
  },
};
</script>

<style></style>
