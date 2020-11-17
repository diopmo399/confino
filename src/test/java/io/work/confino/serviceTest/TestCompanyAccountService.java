package io.work.confino.serviceTest;

import io.work.confino.exceptions.ResourceNotFoundException;
import io.work.confino.models.CompanyAccount;
import io.work.confino.repositories.CompanyAccountMongoRepository;
import io.work.confino.services.CompanyAccountService;
import io.work.confino.services.Impl.CompanyAccountServiceImpl;
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

public class TestCompanyAccountService {

    @Mock
    private CompanyAccountMongoRepository companyAccountMongoRepository;

    private CompanyAccountService companyAccountService;

    @BeforeEach
    void setup(){
        MockitoAnnotations.initMocks(this);
        companyAccountService = new CompanyAccountServiceImpl(companyAccountMongoRepository);
    }

    @Test
    void shouldSavedCompanyAccountSuccessFully(){

        final CompanyAccount companyAccount = TestHelper.COMPANY_ACCOUNTS.get(0);

        given(companyAccountMongoRepository.save(companyAccount)).willAnswer(invocation -> invocation.getArgument(0) );

        CompanyAccount saveCompany = companyAccountService.saveCompanyAccount(companyAccount);

        assertThat(saveCompany).isNotNull();

        verify(companyAccountMongoRepository).save(any(CompanyAccount.class));
    }


    @Test
    void updateCompanyAccount(){
        final CompanyAccount companyAccount = TestHelper.COMPANY_ACCOUNTS.get(0);

        given(companyAccountMongoRepository.findById(companyAccount.getId())).willReturn(Optional.of(companyAccount));
        given(companyAccountMongoRepository.save(companyAccount)).willReturn(companyAccount);

        final CompanyAccount excepted = companyAccountService.updateCompanyAccount(companyAccount);

        assertThat(excepted).isNotNull();

        verify(companyAccountMongoRepository).save(any(CompanyAccount.class));


    }

    @Test
    void shouldThrowErrorWhenUpdateCompanyAccountWithIdNotExisying(){
        final CompanyAccount companyAccount = TestHelper.COMPANY_ACCOUNTS.get(0);

        given(companyAccountMongoRepository.findById(companyAccount.getId())).willReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, ()-> companyAccountService.updateCompanyAccount(companyAccount));

        verify(companyAccountMongoRepository, never()).save(any(CompanyAccount.class));
    }

    @Test
    void shouldReturnFindAll(){
        List<CompanyAccount> companyAccounts = TestHelper.COMPANY_ACCOUNTS;

        given(companyAccountMongoRepository.findAll()).willReturn(companyAccounts);

        List<CompanyAccount> excepted = companyAccountService.getAllCompanyAccount();

        assertEquals(excepted, companyAccounts);
    }

    @Test
    void findCompanyAccountId(){
        final String id = TestHelper.COMPANY_ACCOUNTS.get(0).getId();
        final CompanyAccount companyAccount = TestHelper.COMPANY_ACCOUNTS.get(0);

        given(companyAccountMongoRepository.findById(id)).willReturn(Optional.of(companyAccount));

        final CompanyAccount excepted = companyAccountService.findCompanyAccountById(id);

        assertThat(excepted).isNotNull();
    }

    @Test
    void deleteCompanyAccount(){
        final String companyAccountId = TestHelper.COMPANY_ACCOUNTS.get(0).getId();

        given(companyAccountMongoRepository.findById(companyAccountId)).willReturn(Optional.of(TestHelper.COMPANY_ACCOUNTS.get(0)));

        companyAccountService.deleteCompanyAccount(companyAccountId);
        companyAccountService.deleteCompanyAccount(companyAccountId);

        verify(companyAccountMongoRepository, times(2) ).deleteById(companyAccountId);


    }


}
