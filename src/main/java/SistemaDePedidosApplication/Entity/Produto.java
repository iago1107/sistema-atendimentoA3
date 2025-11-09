package SistemaDePedidosApplication.Entity;

import SistemaDePedidosApplication.Entity.Enum.CategoriaProduto;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "Produto")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String nome;

    @Column(length = 1000) // Permitir uma descrição mais longa
    private String descricao;

    @NotNull
    @Positive // O preço não pode ser zero ou negativo
    @Column(precision = 10, scale = 2) // Define a precisão (ex: 12345678.90)
    private BigDecimal preco;

    /**
     * Define a categoria do produto.
     * @Enumerated(EnumType.STRING) salva a categoria no banco como texto
     * (ex: "PADARIA") em vez de um número (0, 1, 2).
     */
    @NotNull
    @Enumerated(EnumType.STRING)
    private CategoriaProduto categoria;

    private String urlImagem; // Link para a foto do produto

    @NotNull
    @Builder.Default // Se não for informado, o padrão é 'true'
    private Boolean ativo = true; // Permite "desativar" um item do cardápio
    @Column(name = "quantidade_estoque")
    @Builder.Default // Garante que o valor padrão será 0
    private Integer quantidadeEstoque = 0;
}