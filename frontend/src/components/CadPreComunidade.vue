<template>
  <div class="row">
    <div class="col-6 offset-md-3">
      <div class="card">
        <div class="card-header">
          Cadastro de Consumidores
        </div>
        <div class="card-body">
          <form>
            <div class="md-form">
              <input
                autocomplete="off"
                type="email"
                id="nome"
                class="form-control"
                v-model.trim="nome"
              />
              <label for="nome">Nome</label>
            </div>
            <button
              type="button"
              class="btn btn-primary pull-right"
              @click="cadastrar()"
            >
              <i class="fa fa-floppy-o"></i> Cadastrar
            </button>
            <pre>Data: {{ $data }}</pre>
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
    };
  },
};
</script>

<style></style>
