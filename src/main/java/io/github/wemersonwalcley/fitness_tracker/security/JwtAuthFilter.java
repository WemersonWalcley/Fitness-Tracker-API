package io.github.wemersonwalcley.fitness_tracker.security;

import io.github.wemersonwalcley.fitness_tracker.model.CredentialModel;
import io.github.wemersonwalcley.fitness_tracker.repository.CredentialRepository;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtAuthFilter extends OncePerRequestFilter {

    private JwtEncoder jwtEncoder;
    private CredentialRepository repository;

    public JwtAuthFilter(JwtEncoder jwtEncoder, CredentialRepository repository) {
        this.jwtEncoder = jwtEncoder;
        this.repository = repository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = getToken(request);
        boolean isValid = jwtEncoder.isTokenValid(token);
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
        return token.substring(7);
    }

    private void authenticateAccount(String token){
        Long accountId = jwtEncoder.getAccountId(token);
        CredentialModel credentialModel = repository.findById(accountId).orElseThrow(() -> new UsernameNotFoundException("Credential not found by id"));
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(credentialModel, null, credentialModel.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
    }
}
