package io.work.confino.serviceTest;

import io.work.confino.exceptions.ResourceNotFoundException;
import io.work.confino.models.Candidate;
import io.work.confino.repositories.CandidateMongoRepository;
import io.work.confino.services.CandidateService;
import io.work.confino.utilsTest.VARIABLES;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

public class TestCandidateService {

    @Mock
    private CandidateMongoRepository candidateMongoRepository;

    @InjectMocks
    private CandidateService candidateService;

    @BeforeEach
    public void setup(){

        MockitoAnnotations.initMocks(this);

    }

    @Test
    void shouldSavedCandidateSuccesFully(){

        final Candidate candidate = VARIABLES.CANDIDATES.get(0);

        given(candidateMongoRepository.save(candidate)).willAnswer(invocation -> invocation.getArgument(0) );

        Candidate saveCandidate = candidateService.saveCandidate(candidate);

        assertThat(saveCandidate).isNotNull();

        verify(candidateMongoRepository).save(any(Candidate.class));
    }

    @Test
    void updateCandidate(){
        final Candidate candidate = VARIABLES.CANDIDATES.get(0);

        given(candidateMongoRepository.findById(candidate.getId())).willReturn(Optional.of(candidate));
        given(candidateMongoRepository.save(candidate)).willReturn(candidate);

        final Candidate excepted = candidateService.updateCandidate(candidate);

        assertThat(excepted).isNotNull();

        verify(candidateMongoRepository).save(any(Candidate.class));


    }

    @Test
    void shouldThrowErrorWhenUpdateCandidateWithIdNotExisying(){
        final Candidate candidate = VARIABLES.CANDIDATES.get(0);

        given(candidateMongoRepository.findById(candidate.getId())).willReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, ()-> candidateService.updateCandidate(candidate));

        verify(candidateMongoRepository).save(any(Candidate.class));
    }

}
