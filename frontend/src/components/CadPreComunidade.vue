<template>
  <div class="row">
    <div class="col-6 offset-md-3">
      <div class="card">
        <div class="card-header">
          Cadastro de Pre Comunidade
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
              <div class="text-right">
                <button
                  type="button"
                  class="btn btn-primary"
                  @click="cadastrar()"
                >
                <i class="fa fa-floppy-o"></i> Cadastrar
                </button>
              </div>
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
