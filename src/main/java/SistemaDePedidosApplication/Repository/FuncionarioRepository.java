package SistemaDePedidosApplication.Repository;

import SistemaDePedidosApplication.Entity.Usuario.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {

    /**
     * Este método de login funcionará para ambos (Atendente e ADM)
     * pois ambos estão na mesma tabela e compartilham o campo 'email'.
     */
    Optional<Funcionario> findByEmail(String email);
}