package io.work.confino.models;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.io.Serializable;


@Builder
@Getter
@Setter
public class User implements Serializable {

    @Id
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
}
