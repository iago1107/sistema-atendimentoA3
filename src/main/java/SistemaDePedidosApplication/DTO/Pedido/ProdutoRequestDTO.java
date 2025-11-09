package SistemaDePedidosApplication.DTO.Pedido;

import SistemaDePedidosApplication.Entity.Enum.CategoriaProduto;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProdutoRequestDTO {

    @NotBlank(message = "O nome é obrigatório")
    private String nome;

    private String descricao;

    @NotNull(message = "O preço é obrigatório")
    @Positive(message = "O preço deve ser positivo")
    private BigDecimal preco;

    @NotNull(message = "A categoria é obrigatória")
    private CategoriaProduto categoria;

    @NotNull(message = "O estoque é obrigatório")
    @Min(value = 0, message = "O estoque não pode ser negativo")
    private Integer quantidadeEstoque;

    private String urlImagem;
}