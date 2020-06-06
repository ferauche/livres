Vue.component('Checkout', {
    data() {
        return {
            step: 1,
            produtor: '',
            valorTotal: 'R$ 800.000,00',
            nome: '',
            cep: '',
            estado: '',
            cidade: '',
            bairro: '',
            endereco: '',
            numero: '',
            complemento: '',
            modalidadePagamento: '',
            formaPagamento: '',
            produtos: [{
                    nome: 'Prod1',
                    categoria: 'Cat1',
                    preco: 'R$ 99,99',
                    qtd: 10
                },
                {
                    nome: 'Prod2',
                    categoria: 'Cat7',
                    preco: 'R$ 13,99',
                    qtd: 12
                }
            ],
            modalidadesPagamento: [],
            formasPagamento: []
        }
    },
    created() {
        service.getModalidadesPagamento()
            .then(r => this.modalidadesPagamento = r);

        service.getFormasPagamento()
            .then(r => this.formasPagamento = r);
    },
    methods: {
        prev() {
            this.step--;
        },
        next() {
            this.step++;
        },
        finalizarPedido() {
            service.finalizarPedido(this)
                .then(r => this.$toaster.success(r.msg))
                .catch(e => this.$toaster.error(e.msg));
        }
    },
    template: `
		<div class="row">
		    <div class="col" v-show="step === 1">
		        <div class="card">
		            <div class="card-header">
		                Carrinho
		            </div>
		            <div class="card-body">
		            	<div class="table-responsive">
			                <table id="dtBasicExample" class="table table-bordered table-sm" cellspacing="0" width="100%">
			                    <thead class="">
			                        <tr>
			                            <th class="th-sm">Nome</th>
			                            <th class="th-sm">Categoria</th>
			                            <th class="th-sm">Quantidade</th>
			                            <th class="th-sm text-right">Preço</th>
			                        </tr>
			                    </thead>
			                    <tbody>
			                        <tr v-for="produto in produtos">
			                            <td>{{produto.nome}}</td>
			                            <td>{{produto.categoria}}</td>
			                            <td class="text-right">{{produto.qtd}}</td>
			                            <td class="text-right">{{produto.preco}}</td>
			                        </tr>
			                    </tbody>
			                </table>
		                </div>
		                <div class="text-right">
		                    <h4>Valor total: {{ valorTotal }}</h4>
		                </div>
		                <div class="text-right">
		                    <button type="button" class="btn btn-primary" @click="next()">Continuar <i class="fa fa-share"></i></button>
		                </div>
		            </div>
		        </div>
		    </div>
		    <div class="col-8 offset-md-2" v-show="step === 2">
		        <div class="card">
		            <div class="card-header">
		                Dados de Entrega
		            </div>
		            <div class="card-body">
		                <form>
		                    <div class="md-form">
		                        <input autocomplete="off" type="email" id="nome" class="form-control" v-model.trim="nome">
		                        <label for="nome">Nome</label>
		                    </div>
		                    <div class="row">
		                        <div class="col-md-2">
		                            <div class="md-form">
		                                <input autocomplete="off" type="text" id="cep" class="form-control" v-model.trim="cep">
		                                <label for="cep">CEP</label>
		                            </div>
		                        </div>
		                        <div class="col-md-2">
		                            <div class="md-form">
		                                <input autocomplete="off" type="text" id="estado" class="form-control" v-model.trim="estado">
		                                <label for="estado">Estado</label>
		                            </div>
		                        </div>
		                        <div class="col-md-4">
		                            <div class="md-form">
		                                <input autocomplete="off" type="text" id="cidade" class="form-control" v-model.trim="cidade">
		                                <label for="cidade">Cidade</label>
		                            </div>
		                        </div>
		                        <div class="col-md-4">
		                            <div class="md-form">
		                                <input autocomplete="off" type="text" id="bairro" class="form-control" v-model.trim="bairro">
		                                <label for="bairro">Bairro</label>
		                            </div>
		                        </div>
		                    </div>
		                    <div class="row">
		                        <div class="col-md-6">
		                            <div class="md-form">
		                                <input autocomplete="off" type="text" id="endereco" class="form-control" v-model.trim="endereco">
		                                <label for="endereco">Endereço</label>
		                            </div>
		                        </div>
		                        <div class="col-md-2">
		                            <div class="md-form">
		                                <input autocomplete="off" type="text" id="numero" class="form-control" v-model.trim="numero">
		                                <label for="numero">Número</label>
		                            </div>
		                        </div>
		                        <div class="col-md-4">
		                            <div class="md-form">
		                                <input autocomplete="off" type="text" id="complemento" class="form-control" v-model.trim="complemento">
		                                <label for="complemento">Complemento</label>
		                            </div>
		                        </div>
		                    </div>
		                    <div class="text-right">
		                        <button type="button" class="btn btn-primary" @click="prev()">Voltar <i class="fa fa-reply"></i></button>
		                        <button type="button" class="btn btn-primary" @click="next()">Continuar <i class="fa fa-share"></i></button>
		                    </div>
		                    <pre>Data: {{ $data }}</pre>
		                </form>
		            </div>
		        </div>
		    </div>
		    <div class="col-8 offset-md-2" v-show="step === 3">
		        <div class="card">
		            <div class="card-header">
		                Método de Pagamento
		            </div>
		            <div class="card-body">
		                <form>
		                    <div class="pt-3"></div>
		                    <div class="select">
		                        <select class="select-text" required v-model="modalidadePagamento">
		                            <option disabled selected></option>
		                            <option v-for="option in modalidadesPagamento" v-bind:value="option.id">
                                    	{{ option.value }}
                                	</option>
		                        </select>
		                        <span class="select-highlight"></span>
		                        <span class="select-bar"></span>
		                        <label class="select-label">Modalidade de Pagamento:</label>
		                    </div>
		                    <div class="pt-3"></div>
		                    <div class="pt-3"></div>
		                    <div class="select" v-show="modalidadePagamento">
		                        <select class="select-text" required v-model="formaPagamento">
		                            <option disabled selected></option>
		                           <option v-for="option in formasPagamento" v-bind:value="option.id">
                                    	{{ option.value }}
                                	</option>>
		                        </select>
		                        <span class="select-highlight"></span>
		                        <span class="select-bar"></span>
		                        <label class="select-label">Forma de Pagamento:</label>
		                    </div>
		                    <div class="pt-3"></div>
		                    <div class="text-right">
		                        <button type="button" class="btn btn-primary" @click="prev()">Voltar <i class="fa fa-reply"></i></button>
		                        <button type="button" class="btn btn-primary" @click="next()">Continuar <i class="fa fa-share"></i></button>
		                    </div>
		                    <pre>Data: {{ $data }}</pre>
		                </form>
		            </div>
		        </div>
		    </div>
		    <div class="col-8 offset-md-2" v-show="step === 4">
		        <div class="card">
		            <div class="card-header">
		                Resumo do Pedido
		            </div>
		            <div class="card-body">
		                <div class="card">
		                    <div class="card-header">
		                        Dados de Entrega
		                    </div>
		                    <div class="card-body">
		                        <div class="row">
		                            <label class="col"><span class="font-weight-bold">Nome:</span> {{nome}}</label>
		                        </div>
		                        <div class="row">
		                            <label class="col"><span class="font-weight-bold">CEP:</span> {{cep}}</label>
		                        </div>
		                        <div class="row">
		                            <label class="col"><span class="font-weight-bold">UF:</span> {{estado}}</label>
		                        </div>
		                        <div class="row">
		                            <label class="col"><span class="font-weight-bold">Cidade:</span> {{cidade}}</label>
		                        </div>
		                        <div class="row">
		                            <label class="col"><span class="font-weight-bold">Bairro:</span> {{bairro}}</label>
		                        </div>
		                        <div class="row">
		                            <label class="col"><span class="font-weight-bold">Endereço:</span> {{endereco}}</label>
		                        </div>
		                        <div class="row">
		                            <label class="col"><span class="font-weight-bold">Nº:</span> {{numero}}</label>
		                        </div>
		                        <div class="row">
		                            <label class="col"><span class="font-weight-bold">Complemento:</span> {{complemento}}</label>
		                        </div>
		                    </div>
		                </div>
		                <div class="pt-3"></div>
		                <div class="card">
		                    <div class="card-header">
		                        Forma de Pagamento
		                    </div>
		                    <div class="card-body">
		                        <div class="row">
		                            <label class="col"><span class="font-weight-bold">Modalidade:</span> {{modalidadePagamento}}</label>
		                        </div>
		                        <div class="row">
		                            <label class="col"><span class="font-weight-bold">Método:</span> {{formaPagamento}}</label>
		                        </div>
		                    </div>
		                </div>
		                <div class="pt-3"></div>
		                <div class="card">
		                    <div class="card-header">Itens do Pedido</div>
		                    <div class="card-body">
		                    	<div class="table-responsive">
			                        <table id="dtBasicExample" class="table table-bordered table-sm" cellspacing="0" width="100%">
			                            <thead class="">
			                                <tr>
			                                    <th class="th-sm">Nome</th>
			                                    <th class="th-sm">Categoria</th>
			                                    <th class="th-sm">Quantidade</th>
			                                    <th class="th-sm text-right">Preço</th>
			                                </tr>
			                            </thead>
			                            <tbody>
			                                <tr v-for="produto in produtos">
			                                    <td>{{produto.nome}}</td>
			                                    <td>{{produto.categoria}}</td>
			                                    <td class="text-right">{{produto.qtd}}</td>
			                                    <td class="text-right">{{produto.preco}}</td>
			                                </tr>
			                            </tbody>
			                        </table>
		                        </div>
		                        <div class="text-right">
		                            <h4>Valor total: {{ valorTotal }}</h4>
		                        </div>
		                    </div>
		                </div>
		                <div class="pt-2"></div>
		                <div class="text-right">
		                    <button type="button" class="btn btn-success" @click="finalizarPedido()"><i class="fa fa-shopping-cart"></i> Finalizar Pedido</button>
		                </div>
		                <pre>Data: {{ $data }}</pre>
		            </div>
		        </div>
		    </div>
		</div>`
});