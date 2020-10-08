package io.work.confino.services;

import io.work.confino.models.Company;
import io.work.confino.models.CompanyAccount;

import java.util.List;

public interface CompanyService {

    public List<Company> getAllCompanyAccount();
    public Company saveCompanyAccount(CompanyAccount companyAccount);
    public Company updateCompanyAccount(CompanyAccount companyAccount);
    public void deleteCompanyAccount(String id);
}
