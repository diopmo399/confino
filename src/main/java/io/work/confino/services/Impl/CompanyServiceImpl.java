package io.work.confino.services.Impl;

import io.work.confino.models.Company;
import io.work.confino.models.CompanyAccount;
import io.work.confino.repositories.CompanyMongoRepository;
import io.work.confino.services.CompanyService;

import java.util.List;

public class CompanyServiceImpl implements CompanyService {

    private final CompanyMongoRepository companyMongoRepository;

    public CompanyServiceImpl(CompanyMongoRepository companyMongoRepository) {
        this.companyMongoRepository = companyMongoRepository;
    }

    @Override
    public List<Company> getAllCompanyAccount() {
        return companyMongoRepository.findAll();
    }

    @Override
    public CompanyAccount saveCompanyAccount(CompanyAccount companyAccount) {
        return null;
    }

    @Override
    public CompanyAccount updateCompanyAccount(CompanyAccount companyAccount) {
        return null;
    }

    @Override
    public void deleteCompanyAccount(String id) {

    }
}
