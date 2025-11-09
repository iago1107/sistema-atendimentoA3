package SistemaDePedidosApplication.Entity.Usuario;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@DiscriminatorValue("ADMINISTRADOR") // Valor na coluna 'tipo_funcionario'
@Getter
@Setter
@NoArgsConstructor
public class Administrador extends Funcionario {

    // No futuro, podemos adicionar campos específicos do ADM aqui
    // Ex: private String nivelAcesso;
}