<template>
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
        <div class="modal-body">{{ mensagem }}</div>
        <div class="modal-footer">
          <button type="button" class="btn" data-dismiss="modal">Não</button>
          <button type="button" class="btn btn-danger" data-dismiss="modal" @click="confirmar">Sim</button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  props: {
    mensagem: {
      type: String
    }
  },
  methods: {
    confirmar: function() {
      this.$emit("confirmar");
    }
  }
};
</script>