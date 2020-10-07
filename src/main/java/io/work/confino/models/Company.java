package io.work.confino.models;
import io.work.confino.annotation.CascadeSave;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.annotation.Generated;
import java.io.Serializable;
import java.util.List;

@Document(collection = "company")
@Builder
@Getter
@Setter
public class Company implements Serializable {

    @Id
    private String id;
    private String nom;
    private String ville;
    private String pays;
    private String website;
    private String activity;
    private String desciption;

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
