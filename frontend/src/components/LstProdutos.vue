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
                <tr v-for="produto in displayedProdutos" :key="produto.id">
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
          <div class="row">
            <div class="col">
              <nav style="float: right">
                <ul class="pagination2">
                  <li class="page-item">
                    <button
                      type="button"
                      class="page-link"
                      v-if="page != 1"
                      @click="page--"
                    ><i class="fa fa-angle-left" aria-hidden="true"></i></button>
                  </li>
                  <li class="page-item">
                    <button
                      type="button"
                      class="page-link"
                      v-bind:key="pageNumber"
                      v-for="pageNumber in pages.slice(page-1, page+5)"
                      :class="{ active: pageNumber == page }"
                      @click="page = pageNumber"
                    >{{pageNumber}}</button>
                  </li>
                  <li class="page-item">
                    <button
                      type="button"
                      @click="page++"
                      v-if="page < pages.length"
                      class="page-link"
                    ><i class="fa fa-angle-right" aria-hidden="true"></i></button>
                  </li>
                </ul>
              </nav>
              <!-- :click-handler="buscarProdutosPorPagina()" -->
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
      pages: []
    };
  },
  created() {
    const that = this;
    loja.listarCarrinhos(191).then(response => {
      const carrinho = response.data.produtos;
      loja.getProdutosDisponiveisVendaByCategoria(191).then(response => {
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

        that.produtos.map(
          p =>
            (p.qtd = carrinho.find(
              c => c.estoqueProdutorId === p.estoqueId
            ).quantidade)
        );
      });
    });
  },
  computed: {
    displayedProdutos() {
      return this.paginate(this.produtos);
    }
  },
  watch: {
    produtos() {
      this.setPages();
    }
  },
  filters: {
    trimWords(value) {
      return (
        value
          .split(" ")
          .splice(0, 20)
          .join(" ") + "..."
      );
    }
  },
  methods: {
    buscarProdutosPorPagina() {
      const that = this;
      loja.listarCarrinhos(191).then(response => {
        const carrinho = response.data.produtos;
        loja
          .getProdutosDisponiveisVendaByCategoria(191, that.currentPage)
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

            that.produtos.map(
              p =>
                (p.qtd = carrinho.find(
                  c => c.estoqueProdutorId === p.estoqueId
                ).quantidade)
            );
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
    setPages() {
      let numberOfPages = Math.ceil(this.produtos.length / this.perPage);
      for (let index = 1; index <= numberOfPages; index++) {
        this.pages.push(index);
      }
    },
    paginate(produtos) {
      let page = this.page;
      let perPage = this.perPage;
      let from = page * perPage - perPage;
      let to = page * perPage;
      return produtos.slice(from, to);
    }
  }
};
</script>