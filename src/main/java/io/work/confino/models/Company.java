package io.work.confino.models;
import io.work.confino.aspect.CascadeSave;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.List;

@Document(collection = "company")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Company implements Serializable {

    @Id
    private String id;
    private String name;
    private String city;
    private String country;
    private String website;
    private String activity;
    private String description;

    @DBRef
    @CascadeSave
    private List<Candidate> candidates;

    @DBRef
    @CascadeSave
    private List<Job> jobs;

    @DBRef
    @CascadeSave
    private List<CompanyAccount> companyAccounts;



}
