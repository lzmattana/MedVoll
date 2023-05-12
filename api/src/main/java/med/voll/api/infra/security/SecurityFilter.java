package med.voll.api.infra.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import med.voll.api.domain.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component // dizer pro spring carregar classe generica
// extendendo classe spring que trabalha com filter
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UsuarioRepository repository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //recupera o token do cabeçalho
        var tokenJWT = recuperarToken(request);


        if(tokenJWT != null) {
            // guardar o login da pessoa
            var subject = tokenService.getSubject(tokenJWT);
            // considerar que ta logada, autenticação forçada
            var usuario = repository.findByLogin(subject);
            // classe do spring
            // obj para autenticar
            var authentication = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);



        }
        // essa linha garante que o prox filtro seja exec
        filterChain.doFilter(request, response);
    }

    private String recuperarToken(HttpServletRequest request) {
        var autorizationHeader = request.getHeader("Authorization");
        if (autorizationHeader != null){
            return autorizationHeader.replace("Bearer ", "");
        }
        return null;

    }
}
