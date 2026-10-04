package com.dilan.job_application_tracker;

public class JobApplicationNotFoundException extends RuntimeException {

    public JobApplicationNotFoundException(Integer id) {
        super("Job application with id " + id + " not found");
    }
}
