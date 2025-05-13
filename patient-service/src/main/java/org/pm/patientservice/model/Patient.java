package org.pm.patientservice.model;

import java.time.*;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import org.springframework.boot.autoconfigure.web.WebProperties;

import java.util.UUID;

@Entity
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @NotNull

    private String Name;

    @NotNull
    @Email
    @Column(unique = true)

    private String email;

    @NotNull
    private String address;

    @NotNull
    private LocalDate dateOfBirth;

    public LocalDate getDate_of_admission() {
        return date_of_admission;
    }

    public void setDate_of_admission(LocalDate date_of_admission) {
        this.date_of_admission = date_of_admission;
    }

    @NotNull
    private LocalDate date_of_admission;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
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

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }






}
