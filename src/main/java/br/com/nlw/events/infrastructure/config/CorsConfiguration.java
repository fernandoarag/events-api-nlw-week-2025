package br.com.nlw.events.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.NonNull;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfiguration {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(@NonNull CorsRegistry registry) {
                registry.addMapping("/**") //Aplica a configuração CORS a todos os endpoints do aplicativo
                        .allowedOrigins("http://localhost", "http://localhost:3000") //especifica as origens que têm permissão para fazer solicitações de origem cruzada
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                        .allowedHeaders("Content-Type", "X-Requested-With", "accept", "Origin", "Access-Control-Request-Method",
                                "Access-Control-Request-Headers", "Authorization")
                        .exposedHeaders("Access-Control-Allow-Origin", "Access-Control-Allow-Credentials") // Expor o cabeçalho Access-Control-Allow-Origin
                        .allowCredentials(true) //(Cookies, cabeçalhos de autorização, etc.)
                        .maxAge(3600);
            }
        };
    }
}