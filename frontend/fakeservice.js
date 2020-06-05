const service =
    (function() {
        return {
            finalizarPedido: finalizarPedido,
            getAddressByCep: getAddressByCep,
            getFormasPagamento: getFormasPagamento,
            getModalidadesPagamento: getModalidadesPagamento,
            savePreComunidade: savePreComunidade,
            getPreComunidades: getPreComunidades,
            getProdutores: getProdutores,
            getProdutosByProdutorId: getProdutosByProdutorId,
            saveConsumidor: saveConsumidor,
            updateCarrinho: updateCarrinho
        }

        function finalizarPedido(data) {
            return new Promise(function(resolve, reject) {
                if (data) resolve({ msg: 'salvo com sucesso!' })
                else reject({ msg: 'erro' })
            })
        }

        function getAddressByCep() {
            return new Promise(function(resolve, reject) {
                const response = {
                    estado: 'MG',
                    cidade: 'Santos',
                    endereco: 'Rua Murilo'
                }

                resolve(response)
            })
        }

        function getFormasPagamento() {
            return new Promise(function(resolve, reject) {
                const response = [
                    { id: 1, value: 'Débito' },
                    { id: 2, value: 'Crédito' },
                    { id: 3, value: 'Dinheiro' }
                ]

                resolve(response)
            })
        }

        function getModalidadesPagamento() {
            return new Promise(function(resolve, reject) {
                const response = [
                    { id: 1, value: 'Pagamento na Entrega' },
                    { id: 2, value: 'Pagamento Online' }
                ]

                resolve(response)
            })
        }

        function savePreComunidade(data) {
            return new Promise(function(resolve, reject) {
                if (data) resolve({ msg: 'salvo com sucesso!' })
                else reject({ msg: 'erro' })
            })
        }

        function getPreComunidades() {
            return new Promise(function(resolve, reject) {
                const response = [
                    { id: 1, value: 'Comunidade do Gabriel Inacio' },
                    { id: 2, value: 'Comunidade do Murilo Caio' }
                ]
                resolve(response)
            })
        }

        function getProdutores() {
            return new Promise(function(resolve, reject) {
                const response = [
                    { id: 1, value: 'Gabriel Inacio' },
                    { id: 2, value: 'Gabriel Bonfa' },
                    { id: 3, value: 'Murilo' }
                ]

                resolve(response)
            })
        }

        function getProdutosByProdutorId(produtorId) {
            return new Promise(function(resolve, reject) {
                const response = [];

                switch (produtorId) {
                    case 1:
                        response.push({ id: 1, nome: 'Prod1', categoria: 'Cat1', qtdEstoque: 7, preco: 'R$ 7,99', qtd: 0 })
                        response.push({ id: 2, nome: 'Prod2', categoria: 'Cat2', qtdEstoque: 8, preco: 'R$ 12,99', qtd: 0 })
                        response.push({ id: 3, nome: 'Prod3', categoria: 'Cat3', qtdEstoque: 12, preco: 'R$ 12,99', qtd: 0 })
                        break;
                    case 2:
                        response.push({ id: 4, nome: 'Prod4', categoria: 'Cat1', qtdEstoque: 4, preco: 'R$ 99,99', qtd: 0 })
                        response.push({ id: 5, nome: 'Prod6', categoria: 'Cat3', qtdEstoque: 13, preco: 'R$ 12,99', qtd: 0 })
                        break;
                    case 3:
                        response.push({ id: 2, nome: 'Prod2', categoria: 'Cat2', qtdEstoque: 12, preco: 'R$ 9,99', qtd: 0 })
                        response.push({ id: 7, nome: 'Prod5', categoria: 'Cat3', qtdEstoque: 8, preco: 'R$ 14,79', qtd: 0 })
                        response.push({ id: 8, nome: 'Prod7', categoria: 'Cat1', qtdEstoque: 12, preco: 'R$ 15,79', qtd: 0 })
                        response.push({ id: 9, nome: 'Prod8', categoria: 'Cat1', qtdEstoque: 12, preco: 'R$ 4,30', qtd: 0 })
                        break;
                }

                resolve(response);
            })
        }

        function saveConsumidor(data) {
            return new Promise(function(resolve, reject) {
                if (data) resolve({ msg: 'salvo com sucesso!' })
                else reject({ msg: 'erro' })
            })
        }

        function updateCarrinho(data) {
            return new Promise(function(resolve, reject) {
                if (data.qtd < 0) reject({ msg: '#ERRO quantidade negativa' })

                resolve({ msg: 'carrinho atualizado!' });
            })
        }
    })();