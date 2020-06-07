<template>
  <div class="row">
    <div class="col-8 offset-md-2">
      <div class="card">
        <div class="card-header">
          Lista de Produtos
        </div>
        <div class="card-body">
          <div class="pt-2"></div>
          <div class="select">
            <select
              class="select-text"
              required
              v-model.trim="produtor"
              @change="changeProdutor()"
            >
              <option disabled selected></option>
              <option
                v-for="option in produtores"
                :key="option.id"
                v-bind:value="option.id"
              >
                {{ option.value }}
              </option>
            </select>
            <span class="select-highlight"></span>
            <span class="select-bar"></span>
            <label class="select-label">Escolha um Produtor:</label>
          </div>
          <div class="pt-3"></div>
          <div class="table-responsive">
            <table
              id="dtBasicExample"
              class="table table-striped table-bordered table-sm"
              cellspacing="0"
              width="100%"
              v-show="produtos.length"
            >
              <thead class="thead-dark">
                <tr>
                  <th class="th-sm">Nome</th>
                  <th class="th-sm">Categoria</th>
                  <th class="th-sm text-right">Em Estoque</th>
                  <th class="th-sm text-right">Preço</th>
                  <th class="text-center" width="200px">Adicionar/Remover</th>
                </tr>
              </thead>
              <tbody>
                <tr v-for="produto in produtos" :key="produto.id">
                  <td>{{ produto.nome }}</td>
                  <td>{{ produto.categoria }}</td>
                  <td class="text-right">{{ produto.qtdEstoque }}</td>
                  <td class="text-right">{{ produto.preco }}</td>
                  <td style="cursor: pointer;" class="text-center">
                    <input
                      autocomplete="off"
                      type="number"
                      min="0"
                      v-bind:max="produto.qtdEstoque"
                      value="0"
                      v-model="produto.qtd"
                      @change="updateCarrinho(produto)"
                      @input="validarQtdProdEscolhido(produto)"
                    />
                  </td>
                </tr>
              </tbody>
            </table>
          </div>
          <div class="row">
            <div class="col">
              <button
                class="btn btn-primary pull-right"
                @click="$router.push('/checkout')"
              >
                <i class="fa fa-shopping-basket" aria-hidden="true"></i> Ir até
                o Carrinho
              </button>
            </div>
          </div>
          <pre>Data: {{ $data }}</pre>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      produtor: "",
      produtos: [],
      produtores: [],
    };
  },
};
</script>

<style></style>
