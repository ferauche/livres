<template>
  <div class="row">
    <div class="col-md-8 col-sm-12 offset-md-2">
      <div class="card">
        <div class="card-header">Lista de Produtos</div>
        <div class="card-body">
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
                      class="form-control form-control-sm text-right"
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
          <div class="row" v-show="maxPages > 1">
            <div class="col">
              <nav>
                <ul class="pagination2 pagiantionfloat-right">
                  <li class="page-item">
                    <button
                      type="button"
                      class="page-link"
                      v-if="page != 1"
                      @click="page--; buscarProdutosPorPagina(page)"
                    >
                      <i class="fa fa-angle-left" aria-hidden="true"></i>
                    </button>
                  </li>
                  <li class="page-item">
                    <button
                      type="button"
                      class="page-link"
                      v-for="pageNumber in getPages(page)"
                      @click="page = pageNumber; buscarProdutosPorPagina(page)"
                      v-bind:key="pageNumber"
                      v-bind:class="{active: page === pageNumber}"
                    >{{pageNumber}}</button>
                  </li>
                  <li class="page-item">
                    <button
                      type="button"
                      @click="page++; buscarProdutosPorPagina(page)"
                      v-if="page < maxPages"
                      class="page-link"
                    >
                      <i class="fa fa-angle-right" aria-hidden="true"></i>
                    </button>
                  </li>
                </ul>
              </nav>
            </div>
          </div>
          <div class="row">
            <div class="col text-right">
              <button class="btn btn-primary" @click="$router.push('/carinho')">
                <i class="fa fa-shopping-basket" aria-hidden="true"></i> Ir até
                o Carrinho
              </button>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import loja from "@/services/loja.js";

export default {
  data() {
    return {
      produtor: "",
      produtos: [],
      produtores: [],
      page: 1,
      perPage: 5,
      maxPages: 1,
      pages: []
    };
  },
  created() {
    this.buscarProdutosPorPagina(1);
  },
  methods: {
    buscarProdutosPorPagina(pagina) {
      const that = this;
      loja.listarCarrinhos(191).then(response => {
        const carrinho = response.data.produtos;
        loja
          .getProdutosDisponiveisVendaByCategoria(191, pagina)
          .then(response => {
            that.produtos = response.data.produtos;
            that.produtos.map(
              p =>
                (p.preco =
                  "R$ " +
                  p.preco.toLocaleString("pt-BR", {
                    maximumFractionDigits: 2,
                    minimumFractionDigits: 2
                  }))
            );
            if (carrinho.length) {
              that.produtos.map(
                p =>
                  (p.qtd = carrinho.find(
                    c => c.estoqueProdutorId === p.estoqueId
                  ).quantidade)
              );
            }

            let min = response.data.paginaAtual;
            that.maxPages = response.data.totalPaginas;
            for (let index = min; index <= that.maxPages; index++) {
              this.pages.push(index);
            }
          });
      });
    },
    validarQtdProdEscolhido(produto) {
      produto.qtd = Math.abs(produto.qtd);

      if (produto.qtd > produto.qtdEstoque) produto.qtd = produto.qtdEstoque;
    },
    updateCarrinho(produto) {
      const that = this;
      loja.sincronizarProduto(191, produto.estoqueId, produto.qtd).then(() => {
        that.$toaster.success("Carrinho atualizado!");
      });
    },
    getPages(page) {
      let min = page - 1;
      let max = page + 4;

      if (this.maxPages <= 5) { 
        min = 0;
        max = this.maxPages;
      } else {
        if (max < this.maxPages) {
          if (min - 2 > 0) {
            min -= 2;
            max -= 2;
          }else {
            min = 0;
            max = Math.min(this.maxPages, 5);
          }
        } else {
          min -= 2;
          max -= 2;
        }

        if (max - this.maxPages > 0) {
          min -= max - this.maxPages;
          max -= max - this.maxPages;
        }
      }

      min = Math.max(0, min);
      //console.log("min", min, "max", max, "over", max - this.maxPages);

      return this.pages.slice(min, max);
    }
  }
};
</script>