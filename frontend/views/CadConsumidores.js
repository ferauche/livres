Vue.component('CadConsumidores', {
    data() {
        return {
            nome: '',
            sobrenome: '',
            cpf: '',
            senha: '',
            precomunidade: '',
            precomunidades: []
        }
    },
    created() {
        service.getPreComunidades()
            .then(r => this.precomunidades = r);
    },
    methods: {
        cadastrar() {
            service.saveConsumidor(this)
                .then(r => this.$toaster.success(r.msg))
                .catch(e => this.$toaster.error(e.msg));
        }
    },
    template: `
        <div class="row">
            <div class="col-6 offset-md-3">
                <div class="card">
                    <div class="card-header">
                        Cadastro de Consumidores
                    </div>
                    <div class="card-body">
                        <form id="formCadConsumidores">
                            <div class="md-form">
                                <input autocomplete="off" type="text" id="nome" class="form-control" v-model.trim="nome">
                                <label for="nome">Nome</label>
                            </div>
                            <div class="md-form">
                                <input autocomplete="off" type="text" id="sobrenome" class="form-control" v-model.trim="sobrenome">
                                <label for="sobrenome">Sobrenome</label>
                            </div>
                            <div class="md-form">
                                <input autocomplete="off" type="text" id="cpf" class="form-control" v-model.trim="cpf">
                                <label for="cpf">CPF</label>
                            </div>
                            <div class="md-form">
                                <input autocomplete="off" type="password" id="senha" class="form-control" v-model.trim="senha">
                                <label for="senha">Senha</label>
                            </div>
                            <div class="pt-1"></div>
                            <div class="select">
                                <select class="select-text" required v-model="precomunidade">
                                    <option disabled selected></option>
                                    <option v-for="option in precomunidades" v-bind:value="option.id">
                                        {{ option.value }}
                                    </option>>
                                </select>
                                <span class="select-highlight"></span>
                                <span class="select-bar"></span>
                                <label class="select-label">Pré-comunidade:</label>
                            </div>
                            <div class="pt-3"></div>
                            <div class="row">
                                <div class="col text-right">
                                    <button type="button" class="btn btn-primary pull-right" @click="cadastrar()"><i class="fa fa-floppy-o"></i> Cadastrar</button>
                                </div>
                            </div>
                            <pre>Data: {{ $data }}</pre>
                        </form>
                    </div>
                </div>
            </div>
        </div>`
});