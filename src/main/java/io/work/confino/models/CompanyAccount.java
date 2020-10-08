package io.work.confino.models;

import io.work.confino.annotation.CascadeSave;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashSet;
import java.util.List;

@Document
@Getter
@Setter
public class CompanyAccount extends User {

    private String username;
    private String password;

    @DBRef
    @CascadeSave
    private List<Job> jobs;

    @DBRef
    @CascadeSave
    private List<Candidate> candidates;

    @Builder
    public CompanyAccount(String id, String firstName, String lastName, String email, String username,
                          String password, List<Job> job, List<Candidate> candidate) {
        super(id, firstName, lastName, email);
        this.username = username;
        this.password = password;
        this.jobs = job;
        this.candidates = candidate;
    }
}
