package SistemaDePedidosApplication.DTO.Pedido;

import lombok.Data;
import jakarta.validation.constraints.NotNull;
import java.util.List;

@Data
public class PedidoRequestDTO {

    @NotNull
    private Long clienteId;

    @NotNull
    private Long enderecoId; // ID do endereço de entrega

    @NotNull
    private List<ItemPedidoRequestDTO> itens;
}