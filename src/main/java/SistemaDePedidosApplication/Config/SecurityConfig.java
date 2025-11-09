package SistemaDePedidosApplication.Config; // O pacote correto

// Imports do PasswordEncoder
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

// Imports do HttpSecurity (que já estavam lá)
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorize -> authorize
                        // 1. Libera o acesso aos endpoints do Swagger/OpenAPI
                        .requestMatchers(
                                "/docs",
                                "/v3/api-docs/**",
                                "/swagger-ui/**",
                                "/swagger-ui.html",
                                "/login" //  <--- ADICIONE ESTA LINHA
                        ).permitAll() // Permite acesso público a estes padrões

                        // 2. Exige autenticação para todo o resto
                        .anyRequest().authenticated()
                )
                // 3. Configura o formulário de login
                .formLogin(form -> form
                        .loginPage("/login") // Aponta para a página de login
                        .permitAll() // Permite que a lógica do formulário seja processada
                );

        return http.build();
    }
}