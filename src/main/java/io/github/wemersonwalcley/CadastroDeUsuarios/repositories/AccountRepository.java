package io.github.wemersonwalcley.CadastroDeUsuarios.repositories;

import io.github.wemersonwalcley.CadastroDeUsuarios.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
}
