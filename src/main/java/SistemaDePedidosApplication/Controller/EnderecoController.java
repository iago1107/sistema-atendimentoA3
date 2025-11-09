package SistemaDePedidosApplication.Controller;

import SistemaDePedidosApplication.DTO.EnderecoRequestDTO;
import SistemaDePedidosApplication.Entity.Endereco;
import SistemaDePedidosApplication.Service.EnderecoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/clientes/{clienteId}/endereco")
@Tag(name = "Endereços", description = "Endpoints para gerenciar o endereço do cliente")
public class EnderecoController {

    @Autowired
    private EnderecoService enderecoService;

    @PostMapping
    @Operation(summary = "Adiciona ou atualiza o endereço de um cliente")
    public ResponseEntity<Endereco> adicionarOuAtualizarEndereco(
            @PathVariable Long clienteId,
            @Valid @RequestBody EnderecoRequestDTO dto) {

        try {
            Endereco endereco = enderecoService.adicionarOuAtualizarEndereco(clienteId, dto);
            return ResponseEntity.ok(endereco);
        } catch (Exception e) {
            return ResponseEntity.notFound().build(); // Retorna 404 se o cliente não for encontrado
        }
    }

    @GetMapping
    @Operation(summary = "Busca o endereço de um cliente")
    public ResponseEntity<Endereco> getEnderecoDoCliente(@PathVariable Long clienteId) {
        try {
            Endereco endereco = enderecoService.getEnderecoPorCliente(clienteId);
            return ResponseEntity.ok(endereco);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}