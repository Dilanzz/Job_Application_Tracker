package com.dilan.job_application_tracker;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class RequestController {

    ArrayList<JobApplication> jobApplications = new ArrayList<>();

    private JobApplicationRepository repository;

    public RequestController(JobApplicationRepository jobApplicationRepository) {
        this.repository = jobApplicationRepository;
    }

    @GetMapping("/")
    public String getName(){
        return "Hello World!";
    }

    @GetMapping("/applications/{id}")
    public ResponseEntity<JobApplication> getApplication(@PathVariable("id") int id){

        Optional<JobApplication> application = Service.getJobApplicationById(id);

        if (application.isPresent()){
            return ResponseEntity.ok(application.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/applications")
    public ResponseEntity<List<JobApplication>> getJobApplications(){
        return ResponseEntity.ok(Service.getAllApplications());
    }

    @PostMapping("/applications")
    public void createApplication(@RequestBody JobApplication jobApplication){
        Service.saveJobApplication(jobApplication);
        jobApplications.add(jobApplication);

        getInfoApplications();
    }

    @PutMapping("/applications/{id}")
    public ResponseEntity<JobApplication> updateApplication(@PathVariable("id") int id, @RequestBody JobApplication.Status status){
        Optional<JobApplication> application = Service.getJobApplicationById(id);

        if (application.isPresent()){
            JobApplication jobApplication = application.get();
            jobApplication.setStatus(status);
            Service.saveJobApplication(jobApplication);
            return ResponseEntity.ok(jobApplication);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("applications/{id}")
    public ResponseEntity<JobApplication> deleteApplication(@PathVariable("id") int id){
        Optional<JobApplication> application = Service.getJobApplicationById(id);
        if (application.isPresent()){
            Service.deleteJobApplicationById(id);
            return ResponseEntity.ok(application.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    public void getInfoApplications() {
        for(JobApplication application : jobApplications){
            application.getApplicationInfo();
        }
    }
}
