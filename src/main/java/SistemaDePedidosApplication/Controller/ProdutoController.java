package SistemaDePedidosApplication.Controller;

import SistemaDePedidosApplication.DTO.Pedido.ProdutoRequestDTO;
import SistemaDePedidosApplication.Entity.Produto;
import SistemaDePedidosApplication.Service.ProdutoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/produtos")
@Tag(name = "Produtos", description = "Endpoints para gerenciar o cardápio (produtos)")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    // --- Endpoints para CLIENTES ---

    @GetMapping
    @Operation(summary = "Lista todos os produtos ativos (cardápio)")
    public ResponseEntity<List<Produto>> listarProdutosAtivos() {
        List<Produto> produtos = produtoService.listarProdutosAtivos();
        return ResponseEntity.ok(produtos);
    }

    // --- Endpoints para o ADM ---

    @PostMapping
    @Operation(summary = "Cria um novo produto (Requer ADM)")
    public ResponseEntity<Produto> criarProduto(@Valid @RequestBody ProdutoRequestDTO dto) {
        Produto novoProduto = produtoService.criarProduto(dto);
        return ResponseEntity.ok(novoProduto);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualiza um produto existente (Requer ADM)")
    public ResponseEntity<Produto> atualizarProduto(@PathVariable Long id, @Valid @RequestBody ProdutoRequestDTO dto) {
        try {
            Produto produtoAtualizado = produtoService.atualizarProduto(id, dto);
            return ResponseEntity.ok(produtoAtualizado);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Desativa (soft delete) um produto (Requer ADM)")
    public ResponseEntity<Void> desativarProduto(@PathVariable Long id) {
        try {
            produtoService.desativarProduto(id);
            return ResponseEntity.noContent().build(); // Resposta 204 (No Content)
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}