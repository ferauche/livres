package br.com.livresbs.livres.dto;

import br.com.livresbs.livres.model.Pedido;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class PedidoDTO {
    public List<Pedido> pedidos;
}
