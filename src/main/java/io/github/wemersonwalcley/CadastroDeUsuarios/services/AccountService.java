package io.github.wemersonwalcley.CadastroDeUsuarios.services;

import io.github.wemersonwalcley.CadastroDeUsuarios.DTOS.AccountDTO;
import io.github.wemersonwalcley.CadastroDeUsuarios.entities.Account;
import io.github.wemersonwalcley.CadastroDeUsuarios.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    public Page<AccountDTO> findAll(Pageable pageable){
        Page<Account> accountPageable = accountRepository.findAll(pageable);
        return accountPageable.map(AccountDTO::new);
    }

    public Optional<AccountDTO> findById(Long id){
        Optional<Account> account = accountRepository.findById(id);
        return account.map(AccountDTO::new);
    }

    public ResponseEntity delete(Long id){
        Optional<Account> optionalAccount = accountRepository.findById(id);
        if(optionalAccount.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        accountRepository.delete(optionalAccount.get());
        return ResponseEntity.noContent().build();
    }

    public Account save(Account account){
        return accountRepository.save(account);
    }

    public Account update(Long id, Account account){
        Account accountUpdated = accountRepository.findById(id).get();
        accountUpdated.setUsername(account.getUsername());
        accountUpdated.setPassword(account.getPassword());
        accountRepository.save(accountUpdated);
        return accountUpdated;
    }
}
