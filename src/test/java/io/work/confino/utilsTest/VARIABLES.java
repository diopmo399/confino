package io.work.confino.utilsTest;

import io.work.confino.models.Candidate;
import io.work.confino.models.CompanyAccount;
import io.work.confino.models.Job;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class VARIABLES {
     public final static List<Job> JOBS = new ArrayList<>(Arrays.asList(
            Job.builder()
                    .id("15ee")
                    .name("Software Engineer")
                    .typeOfContract("CDD")
                    .build(),

            Job.builder()
                    .id("13op")
                    .name("Backend Developer")
                    .typeOfContract("CDI")
                    .build()
    ));
     public final static List<Candidate> CANDIDATES = new ArrayList<>(Arrays.asList(
            Candidate.builder()
                    .id("45er")
                    .email("diopmo@ept.sn")
                    .firstName("Mohamed")
                    .lastName("DIOP")
                    .build(),
            Candidate.builder()
                    .id("78er")
                    .email("diopmo@ept.sn")
                    .firstName("Karim")
                    .lastName("SOW")
                    .build()
    ));
     public final static List<CompanyAccount> COMPANY_ACCOUNTS = new ArrayList<>(Arrays.asList(
            CompanyAccount.builder()
                    .id("155er")
                    .username("diopmo")
                    .password("gfllfd1556")
                    .build(),
            CompanyAccount.builder()
                    .id("155er")
                    .username("sowkarim")
                    .password("thhhfhfff")
                    .build()
    ));
}
