package io.work.confino.models;

import io.work.confino.annotation.CascadeSave;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashSet;

@Document
@Getter
@Setter
public class CompanyAccount extends User {

    private String username;
    private String password;

    @DBRef
    @CascadeSave
    private HashSet<Job> job;

    @DBRef
    @CascadeSave
    private HashSet<Candidate> candidate;
}
