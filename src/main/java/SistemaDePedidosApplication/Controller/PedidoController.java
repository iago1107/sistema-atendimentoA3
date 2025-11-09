package SistemaDePedidosApplication.Controller;

import SistemaDePedidosApplication.DTO.Pedido.PedidoRequestDTO;
import SistemaDePedidosApplication.Entity.Pedido;
import SistemaDePedidosApplication.Service.PedidoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pedidos")
// 2. AGRUPE SEUS ENDPOINTS COM UMA TAG
@Tag(name = "Pedidos", description = "Endpoints para criar e gerenciar pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @PostMapping
    // 3. DESCREVA O ENDPOINT
    @Operation(summary = "Cria um novo pedido",
            description = "Recebe o ID do cliente, do endereço e a lista de itens. Verifica o estoque e, se OK, cria o pedido.")
    // 4. DESCREVA AS POSSÍVEIS RESPOSTAS
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pedido criado com sucesso",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Pedido.class)) }),

            @ApiResponse(responseCode = "400", description = "Requisição inválida (ex: Estoque insuficiente)",
                    content = @Content),

            @ApiResponse(responseCode = "404", description = "Não encontrado (ex: Cliente ou Produto não existe)",
                    content = @Content)
    })
    public ResponseEntity<?> fazerPedido(@RequestBody PedidoRequestDTO pedidoRequest) {
        try {
            Pedido novoPedido = pedidoService.criarPedido(pedidoRequest);
            // 'ResponseEntity.ok()' corresponde ao "200"
            return ResponseEntity.ok(novoPedido);
        } catch (EntityNotFoundException e) {
            // 'ResponseEntity.notFound()' corresponde ao "404"
            return ResponseEntity.notFound().build();
        } catch (RuntimeException e) {
            // 'ResponseEntity.badRequest()' corresponde ao "400"
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // TODO: Adicionar endpoints para "Acompanhar Pedido" (GET /api/pedidos/{id})
    // TODO: Adicionar endpoints para "Acompanhar Fila" (GET /api/pedidos/fila)
}
