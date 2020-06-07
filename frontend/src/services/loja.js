import { http } from "./config";

export default {

    getProdutosDisponiveisVendaByCategoria: (cpf, pagina) => {
        return http.get("/api/loja/produtos", {
            headers: {
                cpf: cpf
            },
            params: {
                pagina: pagina
            }
        });
    },

    checkout: (cpf) => {
        return http.get("/api/loja/pedidos", {
            headers: {
                cpf: cpf
            }
        });
    },

    sincronizarProduto: (cpf, estoqueProdutorId, quantidade) => {
        return http.post("/api/loja/carrinhos", {
            estoqueProdutorId: estoqueProdutorId,
            quantidade: quantidade
        }, { headers: { cpf: cpf } });
    },
};