// Repository para ItemPedido
package SistemaDePedidosApplication.Repository;

import SistemaDePedidosApplication.Entity.ItemPedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemPedidoRepository extends JpaRepository<ItemPedido, Long> {
    // Geralmente não precisamos de métodos customizados aqui
}