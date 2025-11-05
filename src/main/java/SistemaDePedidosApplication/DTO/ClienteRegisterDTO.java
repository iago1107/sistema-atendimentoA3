package SistemaDePedidosApplication.DTO;

import lombok.Data;
import java.time.LocalDate;

@Data
public class ClienteRegisterDTO {
    private String nome;
    private String sobrenome;
    private String cpf;
    private String email;
    private String senha;
    private String telefone;
    private LocalDate dataNascimento;
}
