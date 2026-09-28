package com.dilan.job_application_tracker;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
public class RequestController {

    ArrayList<JobApplication> jobApplications = new ArrayList<>();

    @GetMapping("/")
    public String getName(){
        return "Hello World!";
    }

    @GetMapping("/applications/{id}")
    public JobApplication getApplication(@PathVariable("id") int id){

        for (JobApplication jobApplication : jobApplications){
            if (jobApplication.getId() == id){
                return jobApplication;
            }
        }

        return null;
    }

    @GetMapping("/applications")
    public ArrayList<JobApplication> getJobApplications(){
        return jobApplications;
    }

    @PostMapping("/applications/{id}/{company}/{role}/{status}")
    public void uploadApplication(@PathVariable("id") int id, @PathVariable("company") String company,
                                   @PathVariable ("role") String role, @PathVariable("status") String status){


    }

    @PostMapping("/applications")
    public void createApplication(@RequestBody JobApplication jobApplication){
        jobApplications.add(jobApplication);
        
        getInfoApplications();
    }

    public void getInfoApplications() {
        for(JobApplication application : jobApplications){
            application.getApplicationInfo();
        }
    }
}
