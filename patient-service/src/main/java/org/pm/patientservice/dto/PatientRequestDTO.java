package org.pm.patientservice.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class PatientRequestDTO {
    @NotBlank(message = "Please enter a name.")
    @Size(max = 100 ,message = "Please names cannot exceed 100 characters.")
    private String name;

    @NotBlank(message = "Please enter an email.")
    @Email(message = "Valid email is required.")
    private String email;

    @NotBlank(message = "Please enter an address")
    private String address;

    @NotBlank(message = "Please enter the date of Birth")
    private String dateOfBirth;

    @NotBlank(message = "Please enter the date of Admission")
    private String dateOfAdmission;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getDateOfAdmission() {
        return dateOfAdmission;
    }

    public void setDateOfAdmission(String dateOfAdmission) {
        this.dateOfAdmission = dateOfAdmission;
    }
}
