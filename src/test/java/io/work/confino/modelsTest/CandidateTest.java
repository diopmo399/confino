package io.work.confino.modelsTest;

import io.work.confino.models.Candidate;
import io.work.confino.models.Job;
import io.work.confino.utilsTest.VARIABLES;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class CandidateTest {

    //TODO: 07/10/2020 implementer les tests

    private final String id = "14f";
    private final String firstName = "Mohamed";
    private final String lastName = "DIOP";
    private final String email = "diopmo@ept.sn";
    private Candidate candidate;



    @BeforeEach
    public void init() {

         candidate =  Candidate.builder()
                 .id(id)
                 .firstName(firstName)
                 .lastName(lastName)
                 .email(email)
                 .jobs(VARIABLES.JOBS)
                 .build();

    }

    @Test
    void crudTest(){

       assertThat(candidate.getId()).isEqualTo(id);
       assertThat(candidate.getFirstName()).isEqualTo(firstName);
       assertThat(candidate.getLastName()).isEqualTo(lastName);
       assertThat(candidate.getEmail()).isEqualTo(email);
       assertThat(candidate.getJobs()).isEqualTo(VARIABLES.JOBS);

    }

}
