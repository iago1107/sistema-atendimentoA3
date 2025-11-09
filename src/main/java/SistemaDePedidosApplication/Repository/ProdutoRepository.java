package SistemaDePedidosApplication.Repository;

import SistemaDePedidosApplication.Entity.Produto;
import SistemaDePedidosApplication.Entity.Enum.CategoriaProduto;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    // Método para buscar todos os produtos de uma categoria
    List<Produto> findByCategoria(CategoriaProduto categoria);

    // Método para buscar todos os produtos que estão ativos
    List<Produto> findByAtivoTrue();
}