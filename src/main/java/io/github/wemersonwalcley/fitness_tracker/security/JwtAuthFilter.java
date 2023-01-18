package io.github.wemersonwalcley.fitness_tracker.security;

import io.github.wemersonwalcley.fitness_tracker.entity.Credential;
import io.github.wemersonwalcley.fitness_tracker.repository.LoginRepository;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtAuthFilter extends OncePerRequestFilter {

    private JwtEncoder jwtEncoder;
    private LoginRepository repository;

    public JwtAuthFilter(JwtEncoder jwtEncoder, LoginRepository repository) {
        this.jwtEncoder = jwtEncoder;
        this.repository = repository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = getToken(request);
        boolean isValid = jwtEncoder.validToken(token);
        if(isValid){
            authenticateAccount(token);
        }

        filterChain.doFilter(request, response);
    }

    private String getToken(HttpServletRequest request){
        String token = request.getHeader("Authorization");
        if(token == null || !token.startsWith("Bearer ")){
            return null;
        }
        return token.substring(7, token.length());
    }

    private void authenticateAccount(String token){
        Long accountId = jwtEncoder.getAccountId(token);
        Credential credential = repository.findById(accountId).orElse(null);
        assert credential != null;
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(credential, null, credential.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
    }
}
