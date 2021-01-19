package io.work.confino.controller;

import io.work.confino.models.Company;
import io.work.confino.services.CompanyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/company")
public class CompanyController {

    private CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping
    public ResponseEntity<List<Company>> getAllCompany(){
        return ResponseEntity.ok(companyService.getAllCompany());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Company> getCompanyById(@PathVariable String id){
        return ResponseEntity.ok(companyService.findCompanyById(id));
    }

    @PostMapping
    public ResponseEntity<Company> saveCompany(@RequestBody Company company){
        return ResponseEntity.status(HttpStatus.CREATED).body(companyService.saveCompany(company));
    }

    @PutMapping
    public ResponseEntity<Company> updateCompany(@RequestBody Company company ){
        return ResponseEntity.ok(companyService.updateCompany(company));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCompany(@PathVariable String id){
        companyService.deleteCompany(id);
        return ResponseEntity.ok(id);
    }

}
