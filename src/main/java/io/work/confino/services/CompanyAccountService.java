package io.work.confino.services;

import io.work.confino.models.Candidate;
import io.work.confino.models.CompanyAccount;

import java.util.List;

public interface CompanyAccountService {

    public List<CompanyAccount> getAllCompanyAccount();
    public CompanyAccount saveCompanyAccount(CompanyAccount companyAccount);
    public CompanyAccount findCompanyAccountById(String id);
    public CompanyAccount updateCompanyAccount(CompanyAccount companyAccount);
    public void deleteCompanyAccount(String id);
}
