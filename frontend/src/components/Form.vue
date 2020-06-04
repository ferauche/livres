<template>
  <div class="container col-lg-10 mt-3">
    <div class="card livres-segundary text-center card-form">
      <div class="card-body text-light">
        <h3>Exemplo Form</h3>
        <p>Formulário livres de exemplo</p>
        <form
          class="validate-form has-validation-callback"
          id="Exemplo"
          name="Exemplo"
          method="POST"
        >
          <div class="form-group">
            <input
              type="text"
              name="campo"
              id="campo"
              class="form-control form-control-lg"
              placeholder="campo"
              data-validation="length"
              data-validation-length="min1"
              data-validation-error-msg="Preencha este campo"
            />
          </div>
          <input
            type="button"
            id="btnEnviar"
            name="btnEnviar"
            value="Enviar"
            class="btn btn-block btn-outline-light"
          />
        </form>
      </div>
    </div>
  </div>
</template>

<style>
</style>