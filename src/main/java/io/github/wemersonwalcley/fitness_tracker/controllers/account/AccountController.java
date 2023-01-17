package io.github.wemersonwalcley.fitness_tracker.controllers.account;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

@Api(tags = "Accounts")
@RestController
@RequestMapping("/api/accounts")
public class AccountController {

//    @Autowired
//    private AccountServiceImpl accountServiceImpl;
//
//    @ApiOperation(value = "findAll")
//    @GetMapping
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "page", dataType = "integer", paramType = "query",
//                    value = "Pagina a ser carregada", defaultValue = "0"),
//            @ApiImplicitParam(name = "size", dataType = "integer", paramType = "query",
//                    value = "Quantidade de registros", defaultValue = "5")
//    })
//    public Page<AccountDTO> findAll(@ApiIgnore(
//            "Ignored because swagger ui shows the wrong params, " +
//                    "instead they are explained in the implicit params"
//    ) Pageable pageable) {
//        return accountServiceImpl.findAll(pageable);
//    }
//
//    @ApiOperation(value = "findById")
//    @GetMapping(value = "/{id}")
//    public ResponseEntity<AccountDTO> findById(@PathVariable Long id) {
//        return accountServiceImpl.findById(id);
//    }

//    @ApiOperation(value = "save")
//    @PostMapping
//    public ResponseEntity<Account> save(@RequestBody @Valid Account account) {
//        return accountServiceImpl.save(account);
//    }
//
//    @ApiOperation(value = "update")
//    @PutMapping(value = "/{id}")
//    public ResponseEntity<Account> update(@PathVariable Long id, @RequestBody Account account) {
//        return accountServiceImpl.update(id, account);
//    }
//
//    @ApiOperation(value = "delete")
//    @DeleteMapping(path = "/{id}")
//    public ResponseEntity<String> delete(@PathVariable Long id) {
//        return accountServiceImpl.delete(id);
//    }

}
