package SistemaDePedidosApplication.Service;


import SistemaDePedidosApplication.DTO.Pedido.ItemPedidoRequestDTO;
import SistemaDePedidosApplication.DTO.Pedido.PedidoRequestDTO;
import SistemaDePedidosApplication.Entity.*; // Importa todas as suas entidades
import SistemaDePedidosApplication.Entity.Enum.StatusPedido;
import SistemaDePedidosApplication.Entity.Usuario.Cliente;
import SistemaDePedidosApplication.Repository.*; // Importa todos os repositórios
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional; // MUITO IMPORTANTE
import lombok.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private EnderecoRepository enderecoRepository;
    @Autowired
    private ProdutoRepository produtoRepository;
    // Não precisamos do ItemPedidoRepository aqui,
    // pois ele será salvo junto com o Pedido (CascadeType.ALL)

    /**
     * @Transactional garante que tudo abaixo
     * execute com sucesso, ou NADA será salvo no banco.
     * Se der erro (ex: falta de estoque), a transação
     * inteira é desfeita (rollback).
     */
    @Transactional
    public Pedido criarPedido(PedidoRequestDTO dto) {

        // 1. Validar Entidades Principais
        Cliente cliente = clienteRepository.findById(dto.getClienteId())
                .orElseThrow(() -> new EntityNotFoundException("Cliente não encontrado"));

        Endereco endereco = enderecoRepository.findById(dto.getEnderecoId())
                .orElseThrow(() -> new EntityNotFoundException("Endereço não encontrado"));

        // TODO: Adicionar validação se o endereço pertence ao cliente

        // 2. Criar o Pedido inicial
        Pedido pedido = new Pedido();
        pedido.setCliente(cliente);
        pedido.setEnderecoEntrega(endereco);
        pedido.setStatus(StatusPedido.PENDENTE); // Status inicial
        pedido.setFormaPagamento(dto.getFormaPagamento()); // Salva o pagamento

        // APAGUE O 'return' QUE ESTAVA AQUI

        // Agora este código será executado
        BigDecimal valorTotalDoPedido = BigDecimal.ZERO;
        List<ItemPedido> itensDoPedido = new ArrayList<>();

        // 3. Processar os Itens (Verificar estoque e calcular total)
        for (ItemPedidoRequestDTO itemDTO : dto.getItens()) {

            Produto produto = produtoRepository.findById(itemDTO.getProdutoId())
                    .orElseThrow(() -> new EntityNotFoundException("Produto ID " + itemDTO.getProdutoId() + " não encontrado"));

            // **A LÓGICA DE ESTOQUE QUE VOCÊ PEDIU**
            if (produto.getQuantidadeEstoque() < itemDTO.getQuantidade()) {
                // Se não tiver estoque, lança exceção e o @Transactional desfaz TUDO
                throw new RuntimeException("Estoque insuficiente para o produto: " + produto.getNome());
            }

            // **DAR BAIXA NO ESTOQUE**
            int novoEstoque = produto.getQuantidadeEstoque() - itemDTO.getQuantidade();
            produto.setQuantidadeEstoque(novoEstoque);
            produtoRepository.save(produto); // Salva o estoque atualizado

            // 4. Criar o ItemPedido
            ItemPedido itemPedido = new ItemPedido();
            itemPedido.setProduto(produto);
            itemPedido.setQuantidade(itemDTO.getQuantidade());
            itemPedido.setPrecoUnitario(produto.getPreco()); // Preço do momento da compra
            itemPedido.setPedido(pedido); // Associa ao pedido principal

            itensDoPedido.add(itemPedido);

            // 5. Calcular o Valor Total
            BigDecimal valorDoItem = produto.getPreco().multiply(new BigDecimal(itemDTO.getQuantidade()));
            valorTotalDoPedido = valorTotalDoPedido.add(valorDoItem);
        }

        // 6. Finalizar o Pedido
        pedido.setItens(itensDoPedido);
        pedido.setValorTotal(valorTotalDoPedido);

        // 7. Salvar o Pedido (e os Itens, graças ao Cascade)
        // ESTE É O ÚNICO 'return' QUE DEVE EXISTIR
        return pedidoRepository.save(pedido);

    }}