package io.work.confino.services;

import io.work.confino.models.Candidate;

import java.util.List;

public interface CandidateService {

    public List<Candidate> getAllCandidate();
    public Candidate saveCandidate(Candidate candidate);
    public Candidate updateCandidate(Candidate candidate);
    public void deleteCandidate(String id);
}
