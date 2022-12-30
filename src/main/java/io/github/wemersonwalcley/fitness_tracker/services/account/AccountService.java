package io.github.wemersonwalcley.fitness_tracker.services.account;

import io.github.wemersonwalcley.fitness_tracker.DTOS.AccountDTO;
import io.github.wemersonwalcley.fitness_tracker.entities.Account;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface AccountService {

    Page<AccountDTO> findAll(Pageable pageable);
    ResponseEntity<AccountDTO> findById(Long id);
    ResponseEntity<String> delete(Long id);
    ResponseEntity<Account> save(Account account);
    ResponseEntity<Account> update(Long id, Account account);

}
