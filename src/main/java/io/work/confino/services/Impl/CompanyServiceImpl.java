package io.work.confino.services.Impl;

import io.work.confino.exceptions.ResourceNotFoundException;
import io.work.confino.models.Company;
import io.work.confino.models.CompanyAccount;
import io.work.confino.repositories.CompanyMongoRepository;
import io.work.confino.services.CompanyService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyServiceImpl implements CompanyService {

    private final CompanyMongoRepository companyMongoRepository;

    public CompanyServiceImpl(CompanyMongoRepository companyMongoRepository) {

        this.companyMongoRepository = companyMongoRepository;

    }

    @Override
    public List<Company> getAllCompany() {
        return companyMongoRepository.findAll();

    }

    @Override
    public Company saveCompany(Company company) {
        return companyMongoRepository.save(company);
    }

    @Override
    public Company findCompanyById(String id) {
        return companyMongoRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Company with id : " + id + " doesn't exist"));
    }

    @Override
    public Company updateCompany(Company company) {
        return companyMongoRepository.findById(company.getId())
                .map(company1 -> {
                    return companyMongoRepository.save(company);
                })
                .orElseThrow(()-> new ResourceNotFoundException("company with id: " +
                        company.getId() + "doesn't exist" ));
    }

    @Override
    public void deleteCompany(String id) {
        companyMongoRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("company with id: " + id + "doesn't exist"));

        companyMongoRepository.deleteById(id);
    }
}
