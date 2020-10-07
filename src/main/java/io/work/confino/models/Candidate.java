package io.work.confino.models;


import io.work.confino.annotation.CascadeSave;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
@Getter
@Setter
public class Candidate extends User {

@DBRef
@CascadeSave
private List<Job> jobs;
}
