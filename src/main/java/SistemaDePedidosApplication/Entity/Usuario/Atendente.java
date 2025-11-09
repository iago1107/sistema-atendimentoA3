package SistemaDePedidosApplication.Entity.Usuario;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@DiscriminatorValue("ATENDENTE") // Valor na coluna 'tipo_funcionario'
@Getter
@Setter
@NoArgsConstructor
public class Atendente extends Funcionario {

    // No futuro, podemos adicionar campos específicos do Atendente aqui
    // Ex: private String matricula;
}