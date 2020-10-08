package io.work.confino.services;

import io.work.confino.models.CompanyAccount;

import java.util.List;

public interface JobService {

    public List<CompanyAccount> getAllCompanyAccount();
    public CompanyAccount saveCompanyAccount(CompanyAccount companyAccount);
    public CompanyAccount updateCompanyAccount(CompanyAccount companyAccount);
    public void deleteCompanyAccount();
}
