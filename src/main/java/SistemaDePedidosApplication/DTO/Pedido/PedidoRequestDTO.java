package SistemaDePedidosApplication.DTO.Pedido;


import SistemaDePedidosApplication.Entity.Enum.FormaDePagamento;
import lombok.Data;
import jakarta.validation.constraints.NotNull;
import java.util.List;

@Data
public class PedidoRequestDTO {

    @NotNull
    private Long clienteId;

    @NotNull
    private Long enderecoId;

    @NotNull
    private List<ItemPedidoRequestDTO> itens;

    @NotNull(message = "A forma de pagamento é obrigatória")
    private FormaDePagamento formaPagamento;
}