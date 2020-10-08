package io.work.confino.modelsTest;

import io.work.confino.models.Job;
import io.work.confino.utils.LevelExperience;
import io.work.confino.utilsTest.VARIABLES;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;


public class JobTest {

    //TODO: 08/10/2020 created Job test

    private final String id = "45df";
    private final String name = "Backend Developer";
    private final List<String> profile = new ArrayList<>(Arrays.asList(
            "Ensure maximum performance and availability of IT tools",
            "Test automation"
    ));
    private final String typeOfContract = "CDI";
    private final LevelExperience levelOfExperience = LevelExperience.INTERMEDIARY;
    private final List<String> languagesRequired = new ArrayList<>(Arrays.asList(
            "English",
            "French"
    ));
    private Job job;

    @BeforeEach
    void init() {

        job = Job.builder()
                .id(id)
                .name(name)
                .typeOfContract(typeOfContract)
                .languagesRequired(languagesRequired)
                .profile(profile)
                .levelOfExperience(levelOfExperience)
                .candidates(VARIABLES.CANDIDATES)
                .build();
    }

    @Test
    void crudTest(){

        assertThat(job.getId()).isEqualTo(id);
        assertThat(job.getName()).isEqualTo(name);
        assertThat(job.getCandidates()).isEqualTo(VARIABLES.CANDIDATES);
        assertThat(job.getLanguagesRequired()).isEqualTo(languagesRequired);
        assertThat(job.getLevelOfExperience()).isEqualTo(levelOfExperience);
        assertThat(job.getProfile()).isEqualTo(profile);
        assertThat(job.getTypeOfContract()).isEqualTo(typeOfContract);
    }
}
