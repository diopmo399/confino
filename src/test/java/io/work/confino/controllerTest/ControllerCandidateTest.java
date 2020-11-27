package io.work.confino.controllerTest;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.work.confino.controller.CandidateController;
import io.work.confino.exceptions.ResourceNotFoundException;
import io.work.confino.models.Candidate;
import io.work.confino.repositories.CandidateMongoRepository;
import io.work.confino.services.CandidateService;
import io.work.confino.services.Impl.CandidateServiceImpl;
import io.work.confino.utilsTest.TestHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
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
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ControllerCandidateTest {

    private MockMvc mvc;

    @Mock
    private ObjectMapper objectMapper;

    @Mock
    private CandidateMongoRepository candidateMongoRepository;

    private CandidateService candidateService;

    private CandidateController candidateController;

    // This object will be magically initialized by the initFields method below.
    private JacksonTester<List<Candidate>> jsonCandidates;

    private List<Candidate> candidateList;

    @BeforeEach
    void setup(){

        this.candidateList = TestHelper.CANDIDATES;

        MockitoAnnotations.initMocks(this);

        this.objectMapper = new ObjectMapper();

        candidateService = new CandidateServiceImpl(candidateMongoRepository);

        candidateController = new CandidateController(candidateService);

        JacksonTester.initFields(this, new ObjectMapper());
        // MockMvc standalone approach
        mvc = MockMvcBuilders.standaloneSetup(candidateController)
                .setControllerAdvice(new ResourceNotFoundException("candidate not found"))
                .build();

    }

    @Test
    void shouldFetchAllCandidate() throws Exception {

        given(candidateService.getAllCandidate()).willReturn(candidateList);

        MockHttpServletResponse response = this.mvc.perform(get("/api/candidate")
                                            .accept(MediaType.APPLICATION_JSON))
                .andReturn()
                .getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo(jsonCandidates.write(candidateList).getJson());

    }

    @Test
    void shouldFetchOneCandidateById() throws Exception {
        final String id = TestHelper.CANDIDATES.get(0).getId();
        final Candidate candidate = TestHelper.CANDIDATES.get(0);

        given(candidateMongoRepository.findById(id)).willReturn(Optional.ofNullable(candidate));

        this.mvc.perform(get("http://localhost:9000/api/candidate/" + id)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName", is(Objects.requireNonNull(candidate).getFirstName())))
                .andExpect(jsonPath("$.lastName", is(candidate.getLastName())))
                .andExpect(jsonPath("$.email", is(candidate.getEmail())));


    }

    @Test
    void shouldReturn404WhenFindCandidateWithIdNotExist() throws Exception {
        final String id = TestHelper.CANDIDATES.get(0).getId();

        given(candidateMongoRepository.findById(id)).willReturn(Optional.empty());

        this.mvc.perform(get("http://localhost/api/candidate" + id))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldCreateNewCandidate() throws Exception {
        given(candidateService.saveCandidate(any(Candidate.class))).willAnswer((invocation) -> invocation.getArgument(0));

        final Candidate candidate = TestHelper.CANDIDATES.get(0);

        this.mvc.perform(post("http://localhost/api/candidate")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(candidate)))
                    .andExpect(status().isCreated())
                    .andExpect(jsonPath("$.id", is(candidate.getId())))
                    .andExpect(jsonPath("$.firstName", is(candidate.getFirstName())))
                    .andExpect(jsonPath("$.lastName", is(candidate.getLastName())))
                    .andExpect(jsonPath("$.email", is(candidate.getEmail())));

    }

    @Test
    void shouldUpdateCandidate() throws Exception {
        Candidate candidate = TestHelper.CANDIDATES.get(0);
        candidate.setFirstName("Konteye");
        final Candidate candidateUpdate = candidate;

        given(candidateMongoRepository.findById(candidateUpdate.getId())).willReturn(Optional.of(candidate));
        given(candidateMongoRepository.save(any(Candidate.class))).willAnswer((invocation) -> invocation.getArgument(0));

        this.mvc.perform(put("http://localhost/api/candidate")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(candidateUpdate)))
                .andExpect(jsonPath("$.firstName", is(candidateUpdate.getFirstName())));



    }

    @Test
    void shouldDeleteCandidate() throws Exception {
        String candidateId = TestHelper.CANDIDATES.get(0).getId();
        Candidate candidate = TestHelper.CANDIDATES.get(0);

        given(candidateMongoRepository.findById(candidateId)).willReturn(Optional.of(candidate));
        doNothing().when(candidateMongoRepository).deleteById(candidate.getId());

        this.mvc.perform(delete("http://localhost/api/candidate/{id}", candidate.getId()))
                                        .andExpect(status().isOk());
    }


}
