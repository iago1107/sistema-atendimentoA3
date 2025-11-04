package PadariaSistema;

import PadariaSistema.entity.Cliente;
import PadariaSistema.entity.ClienteRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;

@SpringBootApplication
public class SistemaAtendimentoApplication implements CommandLineRunner {

	private final ClienteRepository clienteRepository; // injeta o acesso ao banco

	public SistemaAtendimentoApplication(ClienteRepository clienteRepository) {
		this.clienteRepository = clienteRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(SistemaAtendimentoApplication.class, args);
	}

	// Este método roda automaticamente quando o programa inicia
	@Override
	public void run(String... args) throws Exception {

		Cliente cliente1 = new Cliente(
				"Ana",
				"Silva",
				"12345678900",
				"ana@email.com",
				"12345",
				"31999999999",
				LocalDate.of(2000, 5, 10)
		);

		// Salva o cliente no banco
		clienteRepository.save(cliente1);

		System.out.println("✅ Cliente cadastrado com sucesso no banco!");
	}
}
