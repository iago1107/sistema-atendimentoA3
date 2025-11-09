package SistemaDePedidosApplication.DTO.Pedido;

import lombok.Data;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Data // Anotação do Lombok (cria getters, setters, etc.)
public class ItemPedidoRequestDTO {

    @NotNull
    private Long produtoId;

    @NotNull
    @Positive // Deve ser pelo menos 1
    private Integer quantidade;
}