package io.work.confino.services.Impl;

import io.work.confino.exceptions.ResourceNotFoundException;
import io.work.confino.models.Job;
import io.work.confino.repositories.JobMongoRepository;
import io.work.confino.services.JobService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobServiceImpl implements JobService {

    final private JobMongoRepository jobMongoRepository;

    public JobServiceImpl(JobMongoRepository jobMongoRepository) {
        this.jobMongoRepository = jobMongoRepository;
    }

    @Override
    public List<Job> getAllJob() {
        return jobMongoRepository.findAll();
    }

    @Override
    public Job saveJob(Job job) {
        return jobMongoRepository.save(job);
    }

    @Override
    public Job updateJob(Job job) {
        return jobMongoRepository.findById(job.getId())
                .map(job1 -> {
                    return jobMongoRepository.save(job);
                })
                .orElseThrow(()-> new ResourceNotFoundException("Job with id: " + job.getId() + " doesn't exist"));
    }

    @Override
    public void deleteJob(String id) {
        jobMongoRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Job with id: " + id + " doesn't exist"));

        jobMongoRepository.deleteById(id);
    }
}
