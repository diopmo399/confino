package io.work.confino.controller;

import io.work.confino.models.CompanyAccount;
import io.work.confino.services.CompanyAccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/companyaccount")
public class CompanyAccountController {

    final private CompanyAccountService companyAccountService;

    public CompanyAccountController(CompanyAccountService companyAccountService) {
        this.companyAccountService = companyAccountService;
    }

    @GetMapping
    public ResponseEntity<List<CompanyAccount>> getAllCompanyAccount(){
        return ResponseEntity.ok(companyAccountService.getAllCompanyAccount());
    }

    @PostMapping
    public ResponseEntity<CompanyAccount> saveCompanyAccount(@RequestBody CompanyAccount companyAccount){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(companyAccountService.saveCompanyAccount(companyAccount));
    }

    @PutMapping
    public ResponseEntity<CompanyAccount> updateCompanyAccount(@RequestBody CompanyAccount companyAccount){
        return ResponseEntity.ok(companyAccountService.updateCompanyAccount(companyAccount));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCompanyAccount(@PathVariable String id){
        companyAccountService.deleteCompanyAccount(id);
        return ResponseEntity.ok(id);
    }
}
