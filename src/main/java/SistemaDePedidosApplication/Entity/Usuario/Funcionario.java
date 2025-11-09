package SistemaDePedidosApplication.Entity.Usuario;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

/**
 * Classe base abstrata para todos os funcionários.
 * Define os atributos comuns.
 */
@Entity
@Table(name = "Funcionario",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "email") // Email deve ser único
        })
@Inheritance(strategy = InheritanceType.SINGLE_TABLE) // Todos em uma tabela
@DiscriminatorColumn(name = "tipo_funcionario", discriminatorType = DiscriminatorType.STRING) // Coluna que diferencia
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public abstract class Funcionario { // Note que é 'abstract'

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String nome;

    @NotBlank
    @Email
    @Column(nullable = false, unique = true)
    private String email;

    @NotBlank
    @Column(nullable = false)
    private String senha; // Por enquanto, texto puro.
}