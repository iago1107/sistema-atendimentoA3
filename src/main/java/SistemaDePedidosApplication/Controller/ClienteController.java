package SistemaDePedidosApplication.Controller;


import SistemaDePedidosApplication.DTO.ClienteLoginDTO;
import SistemaDePedidosApplication.DTO.ClienteRegisterDTO;
import SistemaDePedidosApplication.Entity.Usuario.Cliente;
import SistemaDePedidosApplication.Service.ClienteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    private final ClienteService service;

    public ClienteController(ClienteService service) {
        this.service = service;
    }

    @PostMapping("/register")
    public ResponseEntity<Cliente> register(@RequestBody ClienteRegisterDTO dto) {
        Cliente salvo = service.cadastrarCliente(dto);
        return ResponseEntity.ok(salvo);
    }

    @PostMapping("/login")
    public ResponseEntity<Boolean> login(@RequestBody ClienteLoginDTO dto) {
        boolean ok = service.fazerLogin(dto);
        return ResponseEntity.ok(ok);
    }

    @PutMapping("/{id}/senha")
    public ResponseEntity<Void> alterarSenha(
            @PathVariable Long id,
            @RequestParam String senhaAntiga,
            @RequestParam String novaSenha) {
        service.alterarSenha(id, senhaAntiga, novaSenha);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<Cliente>> listarTodos() {
        return ResponseEntity.ok(service.listarTodos());
    }
}
