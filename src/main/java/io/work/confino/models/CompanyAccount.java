package io.work.confino.models;

import io.work.confino.aspect.CascadeSave;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    @DBRef
    @CascadeSave
    private Set<Role> roles = new HashSet<>();

    @Builder
    public CompanyAccount(String id, String firstName, String lastName, String email, String username,
                          String password, List<Job> job, List<Candidate> candidate) {
        super(id, firstName, lastName, email);
        this.username = username;
        this.password = password;
        this.jobs = job;
        this.candidates = candidate;
    }
    public CompanyAccount(){}
}
