package com.dilan.job_application_tracker;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
public class JobApplication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotBlank(message = "Company cannot be blank")
    private String company;
    @NotBlank(message = "role cannot be blank")
    private String role;
    @Enumerated(EnumType.STRING)
    private Status status =  Status.PENDING;

    public enum Status {
        PENDING,
        ACCEPTED,
        REJECTED
    }

    public JobApplication() {

    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getCompany() {
        return company;
    }
    public void setCompany(String company) {
        this.company = company;
    }
    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }
    public Status getStatus() {
        return status;
    }
    public void setStatus(Status status) {
        this.status = status;
    }

    public void getApplicationInfo(){
        System.out.println("ID: " + id);
        System.out.println("Company: " + company);
        System.out.println("Role: " + role);
    }
}
