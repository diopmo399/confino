package io.work.confino.modelsTest;

import io.work.confino.models.CompanyAccount;
import io.work.confino.utilsTest.VARIABLES;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;


public class CompanyAccountTest {

    //TODO : 08/10/2020 created Company_Account

    private final String id = "14f";
    private final String firstName = "Mohamed";
    private final String lastName = "DIOP";
    private final String email = "diopmo@ept.sn";
    private final String username = "diopmo";
    private final String password = "maria";
    private CompanyAccount companyAccount;


    @BeforeEach
    void init(){

        companyAccount = CompanyAccount.builder()
                .id(id)
                .firstName(firstName)
                .lastName(lastName)
                .email(email)
                .username(username)
                .password(password)
                .job(VARIABLES.JOBS)
                .candidate(VARIABLES.CANDIDATES)
                .build();
    }

    @Test
    void crudTest(){

        assertThat(companyAccount.getId()).isEqualTo(id);
        assertThat(companyAccount.getFirstName()).isEqualTo(firstName);
        assertThat(companyAccount.getLastName()).isEqualTo(lastName);
        assertThat(companyAccount.getEmail()).isEqualTo(email);
        assertThat(companyAccount.getUsername()).isEqualTo(username);
        assertThat(companyAccount.getPassword()).isEqualTo(password);
        assertThat(companyAccount.getCandidates()).isEqualTo(VARIABLES.CANDIDATES);
        assertThat(companyAccount.getJobs()).isEqualTo(VARIABLES.JOBS);
    }
}
