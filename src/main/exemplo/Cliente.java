package SistemaDePedidosApplication.Entity;

import jakarta.persistence.*; // JPA para mapear tabela
import java.time.LocalDate;

import lombok.AllArgsConstructor;

/*

import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Entity // indica que a classe é uma tabela no banco
@Table(name = "ClientePadaria") // nome da tabela

public class Cliente {

     // ---------- COLUNAS ----------
     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)

     private Long idCliente;
     private String nome;
     private String sobrenome;
     private String cpf;
     private String email;
     private String senha;
     private String telefone;
     private LocalDate dataNascimento;

     public Cliente(String nome, String sobrenome, String cpf, String email, String senha, String telefone, LocalDate dataNascimento) {
          this.nome = nome;
          this.sobrenome = sobrenome;
          this.cpf = cpf;
          this.email = email;
          this.senha = senha;
          this.telefone = telefone;
          this.dataNascimento = dataNascimento;
     }

     // ---------- MÉTODOS ----------
     public void alterarSenha(String novaSenha) {
          this.senha = novaSenha;
     }

     // (em um projeto real, login e cadastro são feitos no banco, via Repository)

     // ---------- GETTERS e SETTERS ----------
     public Long getIdCliente() { return idCliente; }
     public void setIdCliente(Long idCliente) { this.idCliente = idCliente; }

     public String getNome() { return nome; }
     public void setNome(String nome) { this.nome = nome; }

     public String getSobrenome() { return sobrenome; }
     public void setSobrenome(String sobrenome) { this.sobrenome = sobrenome; }

     public String getCpf() { return cpf; }
     public void setCpf(String cpf) { this.cpf = cpf; }

     public String getEmail() { return email; }
     public void setEmail(String email) { this.email = email; }

     public String getSenha() { return senha; }
     public void setSenha(String senha) { this.senha = senha; }

     public String getTelefone() { return telefone; }
     public void setTelefone(String telefone) { this.telefone = telefone; }

     public LocalDate getDataNascimento() { return dataNascimento; }
     public void setDataNascimento(LocalDate dataNascimento) { this.dataNascimento = dataNascimento; }
}
