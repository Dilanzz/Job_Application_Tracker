package com.dilan.job_application_tracker;

import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Service
public class Service {

    JobApplicationRepository repository;

    public Service(JobApplicationRepository jobApplicationRepository) {
        repository = jobApplicationRepository;
    }

    public List<JobApplication> getAllApplications() {
        return repository.findAll();
    }

    public Optional<JobApplication> getJobApplicationById(Integer id) {
        return repository.findById(id);
    }

    public void deleteJobApplicationById(Integer id) {
        repository.deleteById(id);
    }

    public void saveJobApplication(JobApplication jobApplication) {
        repository.save(jobApplication);
    }


}
