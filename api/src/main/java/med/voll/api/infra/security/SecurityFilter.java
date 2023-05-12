package med.voll.api.infra.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component // dizer pro spring carregar classe generica
// extendendo classe spring que trabalha com filter
public class SecurityFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var tokenJWT = recuperarToken(request);
        System.out.println(tokenJWT);
        // essa linha garante que o prox filtro seja exec
        filterChain.doFilter(request, response);
    }

    private String recuperarToken(HttpServletRequest request) {
        var autorizationHeader = request.getHeader("Authorization");
        if(autorizationHeader == null){
            throw new RuntimeException("TOKENJWT NÃO ENVIADO NO CABEÇAO AUTHORIZATION");
        }
        return autorizationHeader.replace("Bearer ", "");
    }
}
