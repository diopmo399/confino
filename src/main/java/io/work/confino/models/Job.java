package io.work.confino.models;

import io.work.confino.aspect.CascadeSave;
import io.work.confino.utils.LevelExperience;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.List;

@Builder
@Getter
@Setter
@Document
@AllArgsConstructor
@NoArgsConstructor
public class Job implements Serializable {

    @Id
    private String id;
    private String name;
    private List<String> profile;
    private String typeOfContract;
    private LevelExperience levelOfExperience;
    private List<String> languagesRequired;

    @DBRef
    @CascadeSave
    private List<Candidate> candidates;
}
