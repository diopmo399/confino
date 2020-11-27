package io.work.confino.serviceTest;

import io.work.confino.exceptions.ResourceNotFoundException;
import io.work.confino.models.Company;
import io.work.confino.repositories.CompanyMongoRepository;
import io.work.confino.services.CompanyService;
import io.work.confino.services.Impl.CompanyServiceImpl;
import io.work.confino.utilsTest.TestHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class TestCompanyService {

    @Mock
    private CompanyMongoRepository companyMongoRepository;

    private CompanyService companyService;

    @BeforeEach
    void setup(){
        MockitoAnnotations.initMocks(this);
        companyService = new CompanyServiceImpl(companyMongoRepository);
    }

    @Test
    void shouldSavedCompanySuccessFully(){

        final Company company = TestHelper.COMPANIES.get(0);

        given(companyMongoRepository.save(company)).willAnswer(invocation -> invocation.getArgument(0) );

        Company saveCompany = companyService.saveCompany(company);

        assertThat(saveCompany).isNotNull();

        verify(companyMongoRepository).save(any(Company.class));
    }


    @Test
    void updateCompany(){
        final Company company = TestHelper.COMPANIES.get(0);

        given(companyMongoRepository.findById(company.getId())).willReturn(Optional.of(company));
        given(companyMongoRepository.save(company)).willReturn(company);

        final Company excepted = companyService.updateCompany(company);

        assertThat(excepted).isNotNull();

        verify(companyMongoRepository).save(any(Company.class));


    }

    @Test
    void shouldThrowErrorWhenUpdateCompanyWithIdNotExisying(){
        final Company company = TestHelper.COMPANIES.get(0);

        given(companyMongoRepository.findById(company.getId())).willReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, ()-> companyService.updateCompany(company));

        verify(companyMongoRepository, never()).save(any(Company.class));
    }

    @Test
    void shouldReturnFindAll(){
        List<Company> companies = TestHelper.COMPANIES;

        given(companyMongoRepository.findAll()).willReturn(companies);

        List<Company> excepted = companyService.getAllCompany();

        assertEquals(excepted, companies);
    }

    @Test
    void findCompanyId(){
        final String id = TestHelper.COMPANIES.get(0).getId();
        final Company company = TestHelper.COMPANIES.get(0);

        given(companyMongoRepository.findById(id)).willReturn(Optional.of(company));

        final Company excepted = companyService.findCompanyById(id);

        assertThat(excepted).isNotNull();
    }

    @Test
    void deleteCompany(){
        final String companyId = TestHelper.COMPANIES.get(0).getId();

        given(companyMongoRepository.findById(companyId)).willReturn(Optional.of(TestHelper.COMPANIES.get(0)));

        companyService.deleteCompany(companyId);
        companyService.deleteCompany(companyId);

        verify(companyMongoRepository, times(2) ).deleteById(companyId);


    }
}
