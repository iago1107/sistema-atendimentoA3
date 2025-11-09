package SistemaDePedidosApplication.Entity;

import SistemaDePedidosApplication.Entity.Usuario.Cliente;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Entity
@Table(name = "Endereco")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String logradouro;

    @NotBlank
    private String numero;

    private String complemento; // Pode ser nulo

    @NotBlank
    private String bairro;

    @NotBlank
    private String cidade;

    @NotBlank
    private String estado;

    @NotBlank
    private String cep;

    private String pontoReferencia; // Pode ser nulo

    /**
     * Define a relação OneToOne.
     * 'FetchType.LAZY' evita que o cliente seja carregado junto com o endereço
     * a menos que seja solicitado.
     * '@JsonIgnore' é crucial para evitar loops infinitos ao converter
     * o objeto para JSON (Cliente -> Endereco -> Cliente...).
     */
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id", referencedColumnName = "id")
    @JsonIgnore
    private Cliente cliente;
}