package SistemaDePedidosApplication.Service;

import SistemaDePedidosApplication.DTO.Pedido.ProdutoRequestDTO;
import SistemaDePedidosApplication.Entity.Produto;
import SistemaDePedidosApplication.Repository.ProdutoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    /**
     * Lista produtos para os clientes verem (apenas os ativos)
     */
    public List<Produto> listarProdutosAtivos() {
        return produtoRepository.findByAtivoTrue();
    }

    /**
     * Usado pelo ADM para criar um novo produto no cardápio
     */
    public Produto criarProduto(ProdutoRequestDTO dto) {
        Produto produto = Produto.builder()
                .nome(dto.getNome())
                .descricao(dto.getDescricao())
                .preco(dto.getPreco())
                .categoria(dto.getCategoria())
                .quantidadeEstoque(dto.getQuantidadeEstoque())
                .urlImagem(dto.getUrlImagem())
                .ativo(true) // Novo produto sempre começa ativo
                .build();
        return produtoRepository.save(produto);
    }

    /**
     * Usado pelo ADM para atualizar um produto
     */
    public Produto atualizarProduto(Long id, ProdutoRequestDTO dto) {
        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Produto não encontrado com id: " + id));

        produto.setNome(dto.getNome());
        produto.setDescricao(dto.getDescricao());
        produto.setPreco(dto.getPreco());
        produto.setCategoria(dto.getCategoria());
        produto.setQuantidadeEstoque(dto.getQuantidadeEstoque());
        produto.setUrlImagem(dto.getUrlImagem());

        return produtoRepository.save(produto);
    }

    /**
     * Usado pelo ADM para "deletar" um produto (Soft Delete)
     */
    public void desativarProduto(Long id) {
        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Produto não encontrado com id: " + id));

        produto.setAtivo(false); // Apenas desativa, não apaga
        produtoRepository.save(produto);
    }
}