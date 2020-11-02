package io.work.confino.services;

import io.work.confino.models.Company;
import io.work.confino.models.CompanyAccount;

import java.util.List;

public interface CompanyService {

    public List<Company> getAllCompany();
    public Company saveCompany(Company company);
    public Company updateCompany(Company company);
    public void deleteCompany(String id);
}
