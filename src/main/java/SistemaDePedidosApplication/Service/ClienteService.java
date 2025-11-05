package SistemaDePedidosApplication.Service;


import SistemaDePedidosApplication.DTO.ClienteLoginDTO;
import SistemaDePedidosApplication.DTO.ClienteRegisterDTO;
import SistemaDePedidosApplication.Entity.Cliente;
import SistemaDePedidosApplication.Repository.ClienteRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;
    private final PasswordEncoder passwordEncoder;

    public ClienteService(ClienteRepository clienteRepository, PasswordEncoder passwordEncoder) {
        this.clienteRepository = clienteRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public Cliente cadastrarCliente(ClienteRegisterDTO dto) {
        if (clienteRepository.existsByEmail(dto.getEmail())) {
            throw new IllegalArgumentException("Email já cadastrado");
        }
        if (clienteRepository.existsByCpf(dto.getCpf())) {
            throw new IllegalArgumentException("CPF já cadastrado");
        }

        Cliente cliente = Cliente.builder()
                .nome(dto.getNome())
                .sobrenome(dto.getSobrenome())
                .cpf(dto.getCpf())
                .email(dto.getEmail())
                .senha(passwordEncoder.encode(dto.getSenha())) // HASH
                .telefone(dto.getTelefone())
                .dataNascimento(dto.getDataNascimento())
                .build();

        return clienteRepository.save(cliente);
    }

    public boolean fazerLogin(ClienteLoginDTO dto) {
        Optional<Cliente> opt = clienteRepository.findByEmail(dto.getEmail());
        if (opt.isEmpty()) return false;
        Cliente c = opt.get();
        return passwordEncoder.matches(dto.getSenha(), c.getSenha());
    }

    @Transactional
    public void alterarSenha(Long clienteId, String senhaAntiga, String novaSenha) {
        Cliente cliente = clienteRepository.findById(clienteId)
                .orElseThrow();
        if (!passwordEncoder.matches(senhaAntiga, cliente.getSenha())) {
            throw new IllegalArgumentException("Senha antiga incorreta");
        }
        cliente.setSenha(passwordEncoder.encode(novaSenha));
        clienteRepository.save(cliente);
    }

    // Placeholder: depende da entidade Pedido
    public List<?> consultarHistoricoPedidos(Long clienteId) {
        // Aqui você faria algo como:
        // return pedidoRepository.findByClienteId(clienteId);
        // Por enquanto devolve null ou lança exceção se preferir.
        throw new UnsupportedOperationException("Implementar consulta de pedidos (PedidoRepository)");
    }

    // Placeholder: Endereço e Pagamento devem ser entidades próprias com FK para Cliente
    @Transactional
    public void adicionarEndereco(Long clienteId, Object enderecoDto) {
        // Implementar: buscar cliente, criar Endereco associado e salvar via EnderecoRepository
        throw new UnsupportedOperationException("Implementar Endereco como entidade");
    }

    @Transactional
    public void adicionarPagamento(Long clienteId, Object pagamentoDto) {
        // Implementar: criar PaymentMethod/Cartao e salvar com FK para Cliente
        throw new UnsupportedOperationException("Implementar PaymentMethod como entidade");
    }

    public List<Cliente> listarTodos() {
        return clienteRepository.findAll();
    }
}
