package com.dilan.job_application_tracker;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RequestController {

    @GetMapping("/")
    public String getName(){
        return "Hello World!";
    }

    @GetMapping("/application")
    public JobApplication getApplication(){
        JobApplication application = new JobApplication(1, "Apple", "FirmwareDev",
                 JobApplication.Status.PENDING);

        return application;
    }
}
