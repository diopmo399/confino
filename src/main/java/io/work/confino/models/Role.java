package io.work.confino.models;

import io.work.confino.utils.ERole;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.io.Serializable;

@Document
@Data
public class Role implements Serializable {

    @Id
    private String id;


    private ERole name;

}
