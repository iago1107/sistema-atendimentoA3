package SistemaDePedidosApplication.Service;

import SistemaDePedidosApplication.DTO.FuncionarioLoginDTO;
import SistemaDePedidosApplication.DTO.FuncionarioRegisterDTO; // Novo Import
import SistemaDePedidosApplication.Entity.Usuario.Administrador; // Novo Import
import SistemaDePedidosApplication.Entity.Usuario.Atendente; // Novo Import
import SistemaDePedidosApplication.Entity.Usuario.Funcionario;
import SistemaDePedidosApplication.Entity.Enum.TipoFuncionario; // Novo Import
import SistemaDePedidosApplication.Repository.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class FuncionarioService {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    // --- MÉTODO DE CADASTRO (O QUE FALTAVA) ---
    public Funcionario registrar(FuncionarioRegisterDTO dto) {

        // 1. Verifica se o email já existe
        if (funcionarioRepository.findByEmail(dto.getEmail()).isPresent()) {
            throw new RuntimeException("Este email já está em uso.");
        }

        Funcionario novoFuncionario;

        // 2. Cria a entidade correta (Atendente ou ADM)
        if (dto.getTipo() == TipoFuncionario.ATENDENTE) {
            novoFuncionario = new Atendente();
        } else {
            novoFuncionario = new Administrador();
        }

        // 3. Define os dados (usando senhas em texto puro, como no seu data.sql)
        novoFuncionario.setNome(dto.getNome());
        novoFuncionario.setEmail(dto.getEmail());
        novoFuncionario.setSenha(dto.getSenha()); // ATENÇÃO: Senha em texto puro

        return funcionarioRepository.save(novoFuncionario);
    }


    // --- MÉTODO DE LOGIN (O QUE JÁ FIZEMOS) ---
    public Funcionario login(FuncionarioLoginDTO dto) {

        Optional<Funcionario> optFuncionario = funcionarioRepository.findByEmail(dto.getEmail());

        if (optFuncionario.isEmpty()) {
            throw new RuntimeException("Email não encontrado");
        }

        Funcionario funcionario = optFuncionario.get();

        if (!funcionario.getSenha().equals(dto.getSenha())) {
            throw new RuntimeException("Senha inválida");
        }

        return funcionario;
    }
}