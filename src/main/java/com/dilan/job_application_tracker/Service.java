package com.dilan.job_application_tracker;

import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public class Service {

    private static JobApplicationRepository repository;

    public static List<JobApplication> getAllApplications() {
        return repository.findAll();
    }

    public static Optional<JobApplication> getJobApplicationById(Integer id) {
        return repository.findById(id);
    }

    public static void deleteJobApplicationById(Integer id) {
        repository.deleteById(id);
    }

    public static void saveJobApplication(JobApplication jobApplication) {
        repository.save(jobApplication);
    }


}
