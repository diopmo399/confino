package io.work.confino.serviceTest;

import io.work.confino.models.CompanyAccount;
import io.work.confino.repositories.CompanyAccountMongoRepository;
import io.work.confino.services.CompanyAccountService;
import io.work.confino.utilsTest.VARIABLES;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

public class TestCompanyAccountService {

    @Mock
    private CompanyAccountMongoRepository companyAccountMongoRepository;

    @InjectMocks
    private CompanyAccountService companyAccountService;

    @BeforeEach
    void setup(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void shouldSavedCompanySuccesFully(){

        final CompanyAccount companyAccount = VARIABLES.COMPANY_ACCOUNTS.get(0);

        given(companyAccountMongoRepository.save(companyAccount)).willAnswer(invocation -> invocation.getArgument(0) );

        CompanyAccount saveCompany = companyAccountService.saveCompanyAccount(companyAccount);

        assertThat(saveCompany).isNotNull();

        verify(companyAccountMongoRepository).save(any(CompanyAccount.class));
    }

}
