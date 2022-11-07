package io.github.wemersonwalcley.CadastroDeUsuarios.DTOS;


import io.github.wemersonwalcley.CadastroDeUsuarios.entities.Account;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AccountDTO {
    private Long id;
    private String username;

    public AccountDTO(Account account){
        this.username = account.getUsername();
        this.id = account.getId();
    }
}
