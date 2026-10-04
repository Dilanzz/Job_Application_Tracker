package com.dilan.job_application_tracker;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class RequestController {

    ArrayList<JobApplication> jobApplications = new ArrayList<>();

    Service service;

    public RequestController(Service service) {
        this.service = service;
    }

    @GetMapping("/")
    public String getName(){
        return "Hello World!";
    }

    @GetMapping("/applications/{id}")
    public ResponseEntity<JobApplication> getApplication(@PathVariable("id") int id){

        Optional<JobApplication> application = service.getJobApplicationById(id);

        return ResponseEntity.ok(application.get());
    }

    @GetMapping("/applications")
    public ResponseEntity<List<JobApplication>> getJobApplications(){
        return ResponseEntity.ok(service.getAllApplications());
    }

    @PostMapping("/applications")
    public ResponseEntity<JobApplication> createApplication(@RequestBody JobApplication jobApplication){
        service.saveJobApplication(jobApplication);
        jobApplications.add(jobApplication);
        getInfoApplications();

        return ResponseEntity.status(HttpStatus.CREATED).body(jobApplication);
    }

    @PutMapping("/applications/{id}")
    public ResponseEntity<JobApplication> updateApplication(@PathVariable("id") int id, @RequestBody JobApplication.Status status){
        Optional<JobApplication> application = service.getJobApplicationById(id);

        JobApplication jobApplication = application.get();
        jobApplication.setStatus(status);
        service.saveJobApplication(jobApplication);
        return ResponseEntity.ok(jobApplication);

    }

    @DeleteMapping("applications/{id}")
    public ResponseEntity<JobApplication> deleteApplication(@PathVariable("id") int id){
        Optional<JobApplication> application = service.getJobApplicationById(id);

        service.deleteJobApplicationById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(application.get());
    }

    public void getInfoApplications() {
        for(JobApplication application : jobApplications){
            application.getApplicationInfo();
        }
    }
}
