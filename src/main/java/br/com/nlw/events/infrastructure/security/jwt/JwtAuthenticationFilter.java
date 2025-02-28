package br.com.nlw.events.infrastructure.security.jwt;

import br.com.nlw.events.application.ports.out.TokenServicePort;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final TokenServicePort tokenService;
    private final UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {
        // Este é o header que contém o token JWT
        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        final String username;

        // Verifica se o header é nulo ou não começa com "Bearer "
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        // Extrai o token JWT do header (remove "Bearer ")
        jwt = authHeader.substring(7);

        // Extrai o username do token
        username = tokenService.extractUsername(jwt);

        // Verifica se o username foi extraído e se o usuário ainda não está autenticado
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            // Carrega os detalhes do usuário do banco de dados
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);

            // Verifica se o token é válido
            if (tokenService.isTokenValid(jwt, username)) {
                // Cria um objeto de autenticação
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities()
                );

                // Adiciona detalhes da requisição ao objeto de autenticação
                authToken.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request)
                );

                // Atualiza o SecurityContextHolder
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }

        // Continua com a cadeia de filtros
        filterChain.doFilter(request, response);
    }
}