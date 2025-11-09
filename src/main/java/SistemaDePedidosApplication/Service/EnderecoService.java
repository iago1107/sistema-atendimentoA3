package SistemaDePedidosApplication.Service;

import SistemaDePedidosApplication.DTO.EnderecoRequestDTO;
import SistemaDePedidosApplication.Entity.Usuario.Cliente;
import SistemaDePedidosApplication.Entity.Endereco;
import SistemaDePedidosApplication.Repository.ClienteRepository;
import SistemaDePedidosApplication.Repository.EnderecoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EnderecoService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private EnderecoRepository enderecoRepository; // Você já deve ter este repositório

    @Transactional
    public Endereco adicionarOuAtualizarEndereco(Long clienteId, EnderecoRequestDTO dto) {

        // 1. Encontrar o cliente
        Cliente cliente = clienteRepository.findById(clienteId)
                .orElseThrow(() -> new EntityNotFoundException("Cliente não encontrado com id: " + clienteId));

        // 2. Verificar se o cliente já tem um endereço (para atualizar)
        Endereco endereco = cliente.getEndereco();

        if (endereco == null) {
            // Se for nulo, cria um novo
            endereco = new Endereco();
            endereco.setCliente(cliente); // Associa ao cliente
        }

        // 3. Atualizar os dados do endereço com o DTO
        endereco.setLogradouro(dto.getLogradouro());
        endereco.setNumero(dto.getNumero());
        endereco.setComplemento(dto.getComplemento());
        endereco.setBairro(dto.getBairro());
        endereco.setCidade(dto.getCidade());
        endereco.setEstado(dto.getEstado());
        endereco.setCep(dto.getCep());
        endereco.setPontoReferencia(dto.getPontoReferencia());

        // 4. Salvar o endereço (o JPA cuida da associação)
        Endereco enderecoSalvo = enderecoRepository.save(endereco);

        // 5. Garantir que o cliente também seja atualizado (na relação)
        cliente.setEndereco(enderecoSalvo);
        clienteRepository.save(cliente);

        return enderecoSalvo;
    }

    @Transactional(readOnly = true)
    public Endereco getEnderecoPorCliente(Long clienteId) {
        Cliente cliente = clienteRepository.findById(clienteId)
                .orElseThrow(() -> new EntityNotFoundException("Cliente não encontrado com id: " + clienteId));

        if (cliente.getEndereco() == null) {
            throw new EntityNotFoundException("Endereço não encontrado para o cliente: " + clienteId);
        }
        return cliente.getEndereco();
    }
}