package SistemaDePedidosApplication.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "ItemPedido")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ItemPedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * O Pedido ao qual este item pertence.
     * @JsonIgnore é MUITO importante aqui para evitar
     * um loop infinito (Pedido -> ItemPedido -> Pedido...)
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pedido_id", nullable = false)
    @JsonIgnore
    private Pedido pedido;

    /**
     * O Produto que foi comprado.
     */
    @ManyToOne(fetch = FetchType.EAGER) // Queremos ver o produto ao carregar o item
    @JoinColumn(name = "produto_id", nullable = false)
    private Produto produto;

    @NotNull
    @Positive // Pelo menos 1 item
    private Integer quantidade;

    /**
     * Preço do produto no momento da compra.
     * É importante salvar isso aqui, pois o preço
     * na tabela 'Produto' pode mudar no futuro.
     */
    @NotNull
    @Column(precision = 10, scale = 2)
    private BigDecimal precoUnitario;

}