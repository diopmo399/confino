package io.work.confino.services;

import io.work.confino.models.CompanyAccount;
import io.work.confino.models.Job;

import java.util.List;

public interface JobService {

    public List<Job> getAllJob();
    public Job saveJob(Job job);
    public Job updateJob(Job job);
    public void deleteJob(String id);
}
