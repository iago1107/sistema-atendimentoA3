// Repository para Pedido
package SistemaDePedidosApplication.Repository;

import SistemaDePedidosApplication.Entity.Pedido;
import SistemaDePedidosApplication.Entity.Usuario.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
    // Para o caso de uso "Acompanhar Pedido"
    List<Pedido> findByClienteId(Long clienteId);
}