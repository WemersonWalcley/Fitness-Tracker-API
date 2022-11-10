package io.github.wemersonwalcley.CadastroDeUsuarios.controllers;

import io.github.wemersonwalcley.CadastroDeUsuarios.DTOS.AccountDTO;
import io.github.wemersonwalcley.CadastroDeUsuarios.entities.Account;
import io.github.wemersonwalcley.CadastroDeUsuarios.services.AccountService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.Optional;

@Api(tags = "Accounts")
@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @ApiOperation(value = "findAll")
    @GetMapping
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", dataType = "integer", paramType = "query",
                    value = "Pagina a ser carregada", defaultValue = "0"),
            @ApiImplicitParam(name = "size", dataType = "integer", paramType = "query",
                    value = "Quantidade de registros", defaultValue = "5")
    })
    public Page<AccountDTO> findAll(@ApiIgnore(
            "Ignored because swagger ui shows the wrong params, " +
                    "instead they are explained in the implicit params"
    ) Pageable pageable) {
        return accountService.findAll(pageable);
    }

    @ApiOperation(value = "findById")
    @GetMapping(value = "/{id}")
    public ResponseEntity<AccountDTO> findById(@PathVariable Long id) {
        Optional<AccountDTO> accountDTO = accountService.findById(id);
        return accountDTO.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @ApiOperation(value = "save")
    @PostMapping
    public ResponseEntity<Account> save(@RequestBody Account account) {
        Account savedAccount = accountService.save(account);
        return ResponseEntity.ok(savedAccount);

    }

    @ApiOperation(value = "update")
    @PutMapping(value = "/{id}")
    public ResponseEntity<Account> update(@PathVariable Long id, @RequestBody Account account) {
        Account updatedAccount = accountService.update(id, account);
        return ResponseEntity.ok(updatedAccount);
    }

    @ApiOperation(value = "delete")
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        return accountService.delete(id);
    }
}
