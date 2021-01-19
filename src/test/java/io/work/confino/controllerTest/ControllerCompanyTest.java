package io.work.confino.controllerTest;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.work.confino.controller.CompanyController;
import io.work.confino.exceptions.ResourceNotFoundException;
import io.work.confino.models.Company;
import io.work.confino.services.CompanyService;
import io.work.confino.utilsTest.TestHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ControllerCompanyTest {

    private MockMvc mvc;

    private ObjectMapper objectMapper;

    @Mock
    private CompanyService companyService;

    @InjectMocks
    private CompanyController companyController;

    // This object will be magically initialized by the initFields method below.
    private JacksonTester<List<Company>> jsonCompanys;

    private List<Company> companyList;

    @BeforeEach
    void setup(){

        this.companyList = TestHelper.COMPANIES;

        MockitoAnnotations.initMocks(this);

        this.objectMapper = new ObjectMapper();

        JacksonTester.initFields(this, new ObjectMapper());
        // MockMvc standalone approach
        mvc = MockMvcBuilders.standaloneSetup(companyController)
                .setControllerAdvice(new ResourceNotFoundException("company not found"))
                .build();

    }

    @Test
    void shouldFetchAllCompany() throws Exception {

        given(companyService.getAllCompany()).willReturn(companyList);

        MockHttpServletResponse response = this.mvc.perform(get("/api/company")
                .accept(MediaType.APPLICATION_JSON))
                .andReturn()
                .getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo(jsonCompanys.write(companyList).getJson());

    }

    @Test
    void shouldFetchOneCompanyById() throws Exception {
        final String id = TestHelper.COMPANIES.get(0).getId();
        final Company company = TestHelper.COMPANIES.get(0);

        given(companyService.findCompanyById(id)).willReturn(company);

        this.mvc.perform(get("http://localhost:9000/api/company/{id}", id)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(Objects.requireNonNull(company).getId())))
                .andExpect(jsonPath("$.activity", is(company.getActivity())))
                .andExpect(jsonPath("$.city", is(company.getCity())))
                .andExpect(jsonPath("$.country", is(company.getCountry())))
                .andExpect(jsonPath("$.candidates[0].firstName", is(company.getCandidates().get(0).getFirstName())))
                .andExpect(jsonPath("$.companyAccounts[0].firstName", is(company.getCompanyAccounts().get(0).getFirstName())))
                .andExpect(jsonPath("$.jobs[0].name", is(company.getJobs().get(0).getName())));


    }


    @Test
    void shouldCreateNewCompany() throws Exception {
        given(companyService.saveCompany(any(Company.class))).willAnswer((invocation) -> invocation.getArgument(0));

        final Company company = TestHelper.COMPANIES.get(0);

        this.mvc.perform(post("http://localhost/api/company")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(company)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", is(company.getId())))
                .andExpect(jsonPath("$.activity", is(company.getActivity())))
                .andExpect(jsonPath("$.city", is(company.getCity())))
                .andExpect(jsonPath("$.country", is(company.getCountry())))
                .andExpect(jsonPath("$.candidates[0].firstName", is(company.getCandidates().get(0).getFirstName())))
                .andExpect(jsonPath("$.companyAccounts[0].firstName", is(company.getCompanyAccounts().get(0).getFirstName())))
                .andExpect(jsonPath("$.jobs[0].name", is(company.getJobs().get(0).getName())));

    }

    @Test
    void shouldUpdateCompany() throws Exception {
        Company company = TestHelper.COMPANIES.get(0);
        company.setCity("France");

        given(companyService.updateCompany(any(Company.class))).willReturn(company);

        this.mvc.perform(put("http://localhost/api/company")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(company)))
                .andExpect(jsonPath("$.city", is(company.getCity())));



    }

    @Test
    void shouldDeleteCompany() throws Exception {
        String companyId = TestHelper.COMPANIES.get(0).getId();

        doNothing().when(companyService).deleteCompany(companyId);

        this.mvc.perform(delete("http://localhost/api/company/{id}", companyId))
                .andExpect(status().isOk());
    }
}
