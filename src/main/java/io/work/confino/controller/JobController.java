package io.work.confino.controller;

import io.work.confino.models.Job;
import io.work.confino.services.JobService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/job")
public class JobController {

    final private JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @GetMapping
    public ResponseEntity<List<Job>> getAllJob(){
        return ResponseEntity.ok(jobService.getAllJob());
    }

    @PostMapping
    public ResponseEntity<Job> saveJob(@RequestBody Job job){
        return ResponseEntity.status(HttpStatus.CREATED).body(jobService.saveJob(job));
    }

    @PutMapping
    public ResponseEntity<Job> updateJob(@RequestBody Job job){
        return ResponseEntity.ok(jobService.updateJob(job));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteJob(@PathVariable String id){
        jobService.deleteJob(id);
        return ResponseEntity.ok(id);
    }
}
