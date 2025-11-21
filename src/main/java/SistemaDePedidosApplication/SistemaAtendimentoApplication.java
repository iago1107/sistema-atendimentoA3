package SistemaDePedidosApplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SistemaAtendimentoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SistemaAtendimentoApplication.class, args);
		System.out.println("Sistema iniciado — conectado ao banco de dados com sucesso!");
	}
}
