package SistemaDePedidosApplication.Entity;

import SistemaDePedidosApplication.Entity.Enum.FormaDePagamento;
import SistemaDePedidosApplication.Entity.Enum.StatusPedido;
import SistemaDePedidosApplication.Entity.Usuario.Cliente;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Pedido")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * O Cliente que fez o pedido.
     */
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    /**
     * O Endereco para onde o pedido será enviado.
     * Um endereço pode ser usado em múltiplos pedidos.
     */
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "endereco_id", nullable = false)
    private Endereco enderecoEntrega;

    /**
     * Lista de itens neste pedido.
     * 'mappedBy = "pedido"' indica que a entidade 'ItemPedido'
     * é a dona da relação.
     *
     * 'CascadeType.ALL' e 'orphanRemoval = true' significam
     * que se o 'Pedido' for salvo ou deletado, os 'Itens'
     * associados também serão.
     */
    @OneToMany(
            mappedBy = "pedido",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.EAGER // Carrega os itens junto com o pedido
    )
    @Builder.Default // Garante que a lista nunca seja nula
    private List<ItemPedido> itens = new ArrayList<>();

    @NotNull
    @Enumerated(EnumType.STRING)
    private StatusPedido status;

    @Builder.Default
    private LocalDateTime dataCriacao = LocalDateTime.now();

    private LocalDateTime dataAtualizacao;

    @NotNull
    @Column(precision = 10, scale = 2)
    private BigDecimal valorTotal;

    // Métodos utilitários (opcional, mas recomendado)
    public void adicionarItem(ItemPedido item) {
        itens.add(item);
        item.setPedido(this); // Mantém a consistência da relação
    }

    public void removerItem(ItemPedido item) {
        itens.remove(item);
        item.setPedido(null);
    }
    @NotNull
    @Enumerated(EnumType.STRING) // Salva o nome (ex: "PIX") no banco
    @Column(name = "forma_pagamento")
    private FormaDePagamento formaPagamento;
}