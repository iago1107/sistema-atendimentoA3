package SistemaDePedidosApplication.Controller;

import SistemaDePedidosApplication.DTO.FuncionarioLoginDTO;
import SistemaDePedidosApplication.DTO.FuncionarioRegisterDTO; // Novo Import
import SistemaDePedidosApplication.Entity.Usuario.Funcionario;
import SistemaDePedidosApplication.Service.FuncionarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/funcionarios")
@Tag(name = "Funcionários", description = "Endpoints para login e cadastro de Atendentes e ADMs")
public class FuncionarioController {

    @Autowired
    private FuncionarioService funcionarioService;

    // --- ENDPOINT DE CADASTRO (NOVO) ---
    @PostMapping("/register")
    @Operation(summary = "Cadastra um novo funcionário (Requer ADM)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Funcionário cadastrado"),
            @ApiResponse(responseCode = "400", description = "Email já em uso")
    })
    public ResponseEntity<Funcionario> registrarFuncionario(@Valid @RequestBody FuncionarioRegisterDTO dto) {
        try {
            Funcionario funcionario = funcionarioService.registrar(dto);
            return ResponseEntity.ok(funcionario);
        } catch (RuntimeException e) {
            // Retorna 400 Bad Request se o email já existir
            return ResponseEntity.status(400).body(null);
        }
    }


    // --- ENDPOINT DE LOGIN (EXISTENTE) ---
    @PostMapping("/login")
    @Operation(summary = "Realiza o login de um funcionário (ADM ou Atendente)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Login bem-sucedido"),
            @ApiResponse(responseCode = "401", description = "Email ou senha inválidos")
    })
    public ResponseEntity<Funcionario> loginFuncionario(@Valid @RequestBody FuncionarioLoginDTO dto) {
        try {
            Funcionario funcionario = funcionarioService.login(dto);
            return ResponseEntity.ok(funcionario);
        } catch (RuntimeException e) {
            return ResponseEntity.status(401).body(null);
        }
    }
}