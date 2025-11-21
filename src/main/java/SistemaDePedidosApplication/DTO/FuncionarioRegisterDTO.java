package SistemaDePedidosApplication.DTO;

import SistemaDePedidosApplication.Entity.Enum.TipoFuncionario;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class FuncionarioRegisterDTO {

    @NotBlank(message = "O nome é obrigatório")
    private String nome;

    @NotBlank(message = "O email é obrigatório")
    @Email
    private String email;

    @NotBlank(message = "A senha é obrigatória")
    private String senha;

    @NotNull(message = "O tipo do funcionário é obrigatório")
    private TipoFuncionario tipo;
}