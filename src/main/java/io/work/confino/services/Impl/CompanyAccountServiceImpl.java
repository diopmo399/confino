package io.work.confino.services.Impl;

import io.work.confino.exceptions.ResourceNotFoundException;
import io.work.confino.models.CompanyAccount;
import io.work.confino.repositories.CompanyAccountMongoRepository;
import io.work.confino.services.CompanyAccountService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyAccountServiceImpl implements CompanyAccountService {

    private final CompanyAccountMongoRepository companyAccountMongoRepository;

    public CompanyAccountServiceImpl(CompanyAccountMongoRepository companyAccountMongoRepository) {
        this.companyAccountMongoRepository = companyAccountMongoRepository;
    }

    @Override
    public List<CompanyAccount> getAllCompanyAccount() {
        return companyAccountMongoRepository.findAll();
    }

    @Override
    public CompanyAccount saveCompanyAccount(CompanyAccount companyAccount) {
        return companyAccountMongoRepository.save(companyAccount);
    }

    @Override
    public CompanyAccount findCompanyAccountById(String id) {
        return companyAccountMongoRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Company Acount with id : " + id + " doesn't exist"));
    }

    @Override
    public CompanyAccount updateCompanyAccount(CompanyAccount companyAccount) {
        return companyAccountMongoRepository.findById(companyAccount.getId())
                .map(companyAccount1 -> companyAccountMongoRepository.save(companyAccount))
                .orElseThrow(()-> new ResourceNotFoundException("Company with id: " +
                        companyAccount.getId() + " does not existe"));
    }

    @Override
    public void deleteCompanyAccount(String id) {
        companyAccountMongoRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Company with id: " +
                                 id + " does not existe"));

        companyAccountMongoRepository.deleteById(id);
    }
}
