package io.work.confino.serviceTest;

import io.work.confino.exceptions.ResourceNotFoundException;
import io.work.confino.models.Job;
import io.work.confino.repositories.JobMongoRepository;
import io.work.confino.services.JobService;
import io.work.confino.services.Impl.JobServiceImpl;
import io.work.confino.utilsTest.TestHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class TestJobService {

    @Mock
    private JobMongoRepository jobMongoRepository;

    @InjectMocks
    private JobServiceImpl jobService;

    @BeforeEach
    void setup(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void shouldSavedJobSuccessFully(){

        final Job job = TestHelper.JOBS.get(0);

        given(jobMongoRepository.save(job)).willAnswer(invocation -> invocation.getArgument(0) );

        Job saveJob = jobService.saveJob(job);

        assertThat(saveJob).isNotNull();

        verify(jobMongoRepository).save(any(Job.class));
    }


    @Test
    void updateJob(){
        final Job job = TestHelper.JOBS.get(0);

        given(jobMongoRepository.findById(job.getId())).willReturn(Optional.of(job));
        given(jobMongoRepository.save(job)).willReturn(job);

        final Job excepted = jobService.updateJob(job);

        assertThat(excepted).isNotNull();

        verify(jobMongoRepository).save(any(Job.class));


    }

    @Test
    void shouldThrowErrorWhenUpdateJobWithIdNotExisying(){
        final Job job = TestHelper.JOBS.get(0);

        given(jobMongoRepository.findById(job.getId())).willReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, ()-> jobService.updateJob(job));

        verify(jobMongoRepository, never()).save(any(Job.class));
    }

    @Test
    void shouldReturnFindAll(){
        List<Job> jobs = TestHelper.JOBS;

        given(jobMongoRepository.findAll()).willReturn(jobs);

        List<Job> excepted = jobService.getAllJob();

        assertEquals(excepted, jobs);
    }

    @Test
    void findJobId(){
        final String id = TestHelper.JOBS.get(0).getId();
        final Job job = TestHelper.JOBS.get(0);

        given(jobMongoRepository.findById(id)).willReturn(Optional.of(job));

        final Job excepted = jobService.finJobById(id);

        assertThat(excepted).isNotNull();
    }

    @Test
    void deleteJob(){
        final String jobId = TestHelper.JOBS.get(0).getId();

        given(jobMongoRepository.findById(jobId)).willReturn(Optional.of(TestHelper.JOBS.get(0)));

        jobService.deleteJob(jobId);
        jobService.deleteJob(jobId);

        verify(jobMongoRepository, times(2) ).deleteById(jobId);


    }
}
