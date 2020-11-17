package io.work.confino.serviceTest;

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

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;


public class TestCandidateService {

    @Mock
    private CandidateMongoRepository candidateMongoRepository;

    private CandidateService candidateService;

    @BeforeEach
    public void setup(){

        MockitoAnnotations.initMocks(this);
        candidateService = new CandidateServiceImpl(candidateMongoRepository);

    }

    @Test
    void shouldSavedCandidateSuccessFully(){

        final Candidate candidate = TestHelper.CANDIDATES.get(0);

        given(candidateMongoRepository.save(candidate)).willAnswer(invocation -> invocation.getArgument(0) );

        Candidate saveCandidate = candidateService.saveCandidate(candidate);

        assertThat(saveCandidate).isNotNull();

        verify(candidateMongoRepository).save(any(Candidate.class));
    }

    @Test
    void updateCandidate(){
        final Candidate candidate = TestHelper.CANDIDATES.get(0);

        given(candidateMongoRepository.findById(candidate.getId())).willReturn(Optional.of(candidate));
        given(candidateMongoRepository.save(candidate)).willReturn(candidate);

        final Candidate excepted = candidateService.updateCandidate(candidate);

        assertThat(excepted).isNotNull();

        verify(candidateMongoRepository).save(any(Candidate.class));


    }

    @Test
    void shouldThrowErrorWhenUpdateCandidateWithIdNotExisying(){
        final Candidate candidate = TestHelper.CANDIDATES.get(0);

        given(candidateMongoRepository.findById(candidate.getId())).willReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, ()-> candidateService.updateCandidate(candidate));

        verify(candidateMongoRepository, never()).save(any(Candidate.class));
    }

    @Test
    void shouldReturnFindAll(){
        List<Candidate> candidates = TestHelper.CANDIDATES;

        given(candidateMongoRepository.findAll()).willReturn(candidates);

        List<Candidate> excepted = candidateService.getAllCandidate();

        assertEquals(excepted, candidates);
    }

    @Test
    void findCandidateId(){
        final String id = TestHelper.CANDIDATES.get(0).getId();
        final Candidate candidate = TestHelper.CANDIDATES.get(0);

        given(candidateMongoRepository.findById(id)).willReturn(Optional.of(candidate));

        final Candidate excepted = candidateService.findCandidateById(id);

        assertThat(excepted).isNotNull();
    }

    @Test
    void deleteCandidate(){
        final String candidateId = TestHelper.CANDIDATES.get(0).getId();

        given(candidateMongoRepository.findById(candidateId)).willReturn(Optional.of(TestHelper.CANDIDATES.get(0)));

        candidateService.deleteCandidate(candidateId);
        candidateService.deleteCandidate(candidateId);

        verify(candidateMongoRepository, times(2) ).deleteById(candidateId);


    }

}
