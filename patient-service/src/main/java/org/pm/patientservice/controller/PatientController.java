package org.pm.patientservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.groups.Default;
import org.pm.patientservice.dto.PatientRequestDTO;
import org.pm.patientservice.dto.PatientResponseDTO;
import org.pm.patientservice.dto.validators.CreatePatientValidationGroup;
import org.pm.patientservice.service.PatientService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/patients")
@Tag(name = "Patient Controller" , description = "API for managing patients")

public class PatientController {
    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping
    @Operation(summary = "Get List of patients.")
    public ResponseEntity<List<PatientResponseDTO>> getPatients() {
        List<PatientResponseDTO> patients = patientService.getPatients();
        return ResponseEntity.ok().body(patients);
    }

    @PostMapping
    @Operation(summary = "Create a patient")
    public ResponseEntity<PatientResponseDTO>
    createPatient(@Validated({Default.class, CreatePatientValidationGroup.class}) @RequestBody PatientRequestDTO patientRequestDTO)
    {
        PatientResponseDTO patientResponseDTO = patientService.createPatient(patientRequestDTO);
        return ResponseEntity.ok().body(patientResponseDTO);
    }

    // Anytime we are updating we use the @Put request,and we need id also.
// @Path takes that id and uses it in the method.
    @PutMapping("/{id}")
    @Operation(summary = "Update a patient")
    public ResponseEntity<PatientResponseDTO>
    updatePatient(@PathVariable UUID id,
                 @Validated({Default.class}) @RequestBody PatientRequestDTO patientRequestDTO)
    {
        PatientResponseDTO patientResponseDTO =
                patientService.updatePatient(id,patientRequestDTO);

        return ResponseEntity.ok().body(patientResponseDTO);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Remove a patient")
    public ResponseEntity<Void> deletePatient(@PathVariable UUID id)
    {
        patientService.deletePatient(id);
        return ResponseEntity.noContent().build();
    }


}
