package io.work.confino.services.Impl;

import io.work.confino.exceptions.ResourceNotFoundException;
import io.work.confino.models.Candidate;
import io.work.confino.repositories.CandidateMongoRepository;
import io.work.confino.services.CandidateService;

import java.util.List;

public class CandidateServiceImpl implements CandidateService {

    private final CandidateMongoRepository candidateMongoRepository;

    public CandidateServiceImpl(CandidateMongoRepository candidateMongoRepository) {
        this.candidateMongoRepository = candidateMongoRepository;
    }

    @Override
    public List<Candidate> getAllCandidate() {
        return candidateMongoRepository.findAll();
    }

    @Override
    public Candidate saveCandidate(Candidate candidate) {
        return candidateMongoRepository.save(candidate);
    }

    @Override
    public Candidate updateCandidate(Candidate candidate) {
        return candidateMongoRepository.findById(candidate.getId())
                .map(candidate1 -> {
                    return candidateMongoRepository.save(candidate);
                })
                .orElseThrow(()-> new ResourceNotFoundException("Candidate with id: " +
                        candidate.getId() +" does not exist"));
    }

    @Override
    public void deleteCandidate( String id) {
        candidateMongoRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Candidate with id: " +
                        id +" does not exist"));
        candidateMongoRepository.deleteById(id);
    }
}
