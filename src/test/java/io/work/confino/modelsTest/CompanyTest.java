package io.work.confino.modelsTest;

import io.work.confino.models.Company;
import io.work.confino.utilsTest.TestHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;


public class CompanyTest {

    //TODO: 08/10/2020 Created Company test

    private final String id = "147bf";
    private final String name = "YEREL";
    private final String website = "www.yerel.co";
    private final String city = "Dakar";
    private final String country = "Senegal";
    private final String activity = "IT";
    private final String description = "you will job to write test";
    private Company company;

    @BeforeEach
    void init() {
       company = Company.builder()
                .id(id)
                .name(name)
                .activity(activity)
                .website(website)
                .city(city)
                .country(country)
                .description(description)
                .candidates(TestHelper.CANDIDATES)
                .jobs(TestHelper.JOBS)
                .companyAccounts(TestHelper.COMPANY_ACCOUNTS).build();
    }

    @Test
    void crudTest(){

        assertThat(company.getId()).isEqualTo(id);
        assertThat(company.getActivity()).isEqualTo(activity);
        assertThat(company.getCandidates()).isEqualTo(TestHelper.CANDIDATES);
        assertThat(company.getJobs()).isEqualTo(TestHelper.JOBS);
        assertThat(company.getCompanyAccounts()).isEqualTo(TestHelper.COMPANY_ACCOUNTS);
        assertThat(company.getName()).isEqualTo(name);
        assertThat(company.getCity()).isEqualTo(city);
        assertThat(company.getCountry()).isEqualTo(country);
        assertThat(company.getWebsite()).isEqualTo(website);
        assertThat(company.getDescription()).isEqualTo(description);
    }



}
