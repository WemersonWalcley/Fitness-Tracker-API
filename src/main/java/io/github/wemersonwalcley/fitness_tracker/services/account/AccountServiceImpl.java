package io.github.wemersonwalcley.fitness_tracker.services.account;

import io.github.wemersonwalcley.fitness_tracker.DTOS.AccountDTO;
import io.github.wemersonwalcley.fitness_tracker.DTOS.CredentialsDTO;
import io.github.wemersonwalcley.fitness_tracker.DTOS.TokenDTO;
import io.github.wemersonwalcley.fitness_tracker.entities.Account;
import io.github.wemersonwalcley.fitness_tracker.repositories.AccountRepository;
import io.github.wemersonwalcley.fitness_tracker.security.JwtEncoder;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
@AllArgsConstructor
public class AccountServiceImpl implements AccountService {


    private final AccountRepository accountRepository;
    private final JwtEncoder jwtEncoder;
    private final PasswordEncoder passwordEncoder;

    public Page<AccountDTO> findAll(Pageable pageable) {
        Page<Account> accountPageable = accountRepository.findAll(pageable);
        return accountPageable.map(AccountDTO::new);
    }

    public ResponseEntity<AccountDTO> findById(Long id) {
        Optional<Account> account = accountRepository.findById(id);
        Optional<AccountDTO> accountDTO = account.map(AccountDTO::new);
        return accountDTO
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado"));
    }

    public ResponseEntity<String> delete(Long id) {
        Account account = accountRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado"));
        accountRepository.delete(account);
        return ResponseEntity.status(HttpStatus.OK).body("Usuário deletado com sucesso");
    }

    @Transactional
    public ResponseEntity<Account> save(Account account) {
        String passCrypt = passwordEncoder.encode(account.getPassword());
        account.setPassword(passCrypt);

        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(accountRepository.save(account));
        } catch (ResponseStatusException e) {
            e.printStackTrace();
            throw new ResponseStatusException(e.getStatus(), e.getMessage());
        }
    }

    @Transactional
    public ResponseEntity<Account> update(Long id, Account account) {
        Account accountUpdated = accountRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado"));
        try {
            accountUpdated.setEmail(account.getEmail());
            accountUpdated.setPassword(account.getPassword());
            return ResponseEntity.ok(accountRepository.save(accountUpdated));
        } catch (ResponseStatusException e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Dados incorretos. Verifique os dados da requisisão");
        }
    }

    public ResponseEntity<TokenDTO> authenticate(CredentialsDTO dto) {
        Optional<Account> user = Optional.ofNullable(accountRepository.findByEmail(dto.getEmail()).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Usuário não encontrado.")));
        boolean matches = passwordEncoder.matches(dto.getPassword(), user.get().getPassword());

        if(matches){
            String token = jwtEncoder.generateToken(user.get());
            TokenDTO tokenDTO = new TokenDTO();
            tokenDTO.setToken(token);
            tokenDTO.setLogin(dto.getEmail());
            return ResponseEntity.ok(tokenDTO);
        }else{
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Dados incorretos. Verifique os dados da requisisão");
        }
    }
}

