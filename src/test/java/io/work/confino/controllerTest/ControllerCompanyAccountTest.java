package io.work.confino.controllerTest;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.work.confino.controller.CompanyAccountController;
import io.work.confino.exceptions.ResourceNotFoundException;
import io.work.confino.models.CompanyAccount;
import io.work.confino.services.CompanyAccountService;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ControllerCompanyAccountTest {

    private MockMvc mvc;

    private ObjectMapper objectMapper;

    @Mock
    private CompanyAccountService companyAccountService;

    @InjectMocks
    private CompanyAccountController companyAccountController;

    // This object will be magically initialized by the initFields method below.
    private JacksonTester<List<CompanyAccount>> jsonCompanyAccounts;

    private List<CompanyAccount> companyAccountList;

    @BeforeEach
    void setup(){

        this.companyAccountList = TestHelper.COMPANY_ACCOUNTS;

        MockitoAnnotations.initMocks(this);

        this.objectMapper = new ObjectMapper();

        JacksonTester.initFields(this, new ObjectMapper());
        // MockMvc standalone approach
        mvc = MockMvcBuilders.standaloneSetup(companyAccountController)
                .setControllerAdvice(new ResourceNotFoundException("companyAccount not found"))
                .build();

    }

    @Test
    void shouldFetchAllCompanyAccount() throws Exception {

        given(companyAccountService.getAllCompanyAccount()).willReturn(companyAccountList);

        MockHttpServletResponse response = this.mvc.perform(get("/api/companyaccount")
                .accept(MediaType.APPLICATION_JSON))
                .andReturn()
                .getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo(jsonCompanyAccounts.write(companyAccountList).getJson());

    }

    @Test
    void shouldFetchOneCompanyAccountById() throws Exception {
        final String id = TestHelper.COMPANY_ACCOUNTS.get(0).getId();
        final CompanyAccount companyAccount = TestHelper.COMPANY_ACCOUNTS.get(0);

        given(companyAccountService.findCompanyAccountById(id)).willReturn(companyAccount);

        this.mvc.perform(get("http://localhost:9000/api/companyaccount/" + id)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName", is(Objects.requireNonNull(companyAccount).getFirstName())))
                .andExpect(jsonPath("$.lastName", is(companyAccount.getLastName())))
                .andExpect(jsonPath("$.email", is(companyAccount.getEmail())));


    }


    @Test
    void shouldCreateNewCompanyAccount() throws Exception {
        given(companyAccountService.saveCompanyAccount(any(CompanyAccount.class))).willAnswer((invocation) -> invocation.getArgument(0));

        final CompanyAccount companyAccount = TestHelper.COMPANY_ACCOUNTS.get(0);

        this.mvc.perform(post("http://localhost/api/companyaccount")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(companyAccount)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", is(companyAccount.getId())))
                .andExpect(jsonPath("$.firstName", is(companyAccount.getFirstName())))
                .andExpect(jsonPath("$.lastName", is(companyAccount.getLastName())))
                .andExpect(jsonPath("$.email", is(companyAccount.getEmail())));

    }

    @Test
    void shouldUpdateCompanyAccount() throws Exception {
        CompanyAccount companyAccount = TestHelper.COMPANY_ACCOUNTS.get(0);
        companyAccount.setFirstName("Konteye");
        final CompanyAccount companyAccountUpdate = companyAccount;

        given(companyAccountService.updateCompanyAccount(any(CompanyAccount.class))).willAnswer((invocation) -> invocation.getArgument(0));

        this.mvc.perform(put("http://localhost/api/companyaccount")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(companyAccountUpdate)))
                .andExpect(jsonPath("$.firstName", is(companyAccountUpdate.getFirstName())));



    }

    @Test
    void shouldDeleteCompanyAccount() throws Exception {
        String companyAccountId = TestHelper.COMPANY_ACCOUNTS.get(0).getId();
        CompanyAccount companyAccount = TestHelper.COMPANY_ACCOUNTS.get(0);

        given(companyAccountService.findCompanyAccountById(companyAccountId)).willReturn(companyAccount);

        this.mvc.perform(delete("http://localhost/api/companyaccount/{id}", companyAccount.getId()))
                .andExpect(status().isOk());
    }


}
