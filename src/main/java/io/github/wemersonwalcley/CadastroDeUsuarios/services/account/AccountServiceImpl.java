package io.github.wemersonwalcley.CadastroDeUsuarios.services.account;

import io.github.wemersonwalcley.CadastroDeUsuarios.DTOS.AccountDTO;
import io.github.wemersonwalcley.CadastroDeUsuarios.entities.Account;
import io.github.wemersonwalcley.CadastroDeUsuarios.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService{

    @Autowired
    private AccountRepository accountRepository;

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
            accountUpdated.setUsername(account.getUsername());
            accountUpdated.setPassword(account.getPassword());
            return ResponseEntity.ok(accountRepository.save(accountUpdated));
        } catch (ResponseStatusException e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Dados incorretos. Verifique os dados da requisisão");
        }
    }
}

