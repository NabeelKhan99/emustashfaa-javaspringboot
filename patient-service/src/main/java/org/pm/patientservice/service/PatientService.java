package org.pm.patientservice.service;

import org.pm.patientservice.dto.PatientRequestDTO;
import org.pm.patientservice.dto.PatientResponseDTO;
import org.pm.patientservice.exception.EmailAlreadyExistsException;
import org.pm.patientservice.exception.PatientNotFoundException;
import org.pm.patientservice.mapper.PatientMapper;
import org.pm.patientservice.model.Patient;
import org.pm.patientservice.repository.PatientRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;


import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
/*
This is the bussiness logic where we handle such as checking for unique emails, phone number registered
this layer is hiddedn through the use of abstraction. That is why in this project we are using DTOs to
hide this layer from the front end.
 */

@Service // this tag is sprinboot logic to determine what each class is doing.

public class PatientService {
    private final PatientRepository patientRepository;
    private final PatientRepository patRepo; // Patient Repository object.?
    public PatientService(PatientRepository patRepo, PatientRepository patientRepository)
    {
        this.patRepo = patRepo;
        this.patientRepository = patientRepository;
    }
   //list all patients.

    public  List<PatientResponseDTO> getPatients()
    {
        List<Patient> patients = patRepo.findAll();
        return patients.stream().map(PatientMapper::toDTO).toList();
    }

    //Creates a patient.
    public PatientResponseDTO createPatient(PatientRequestDTO patientRequestDTO)
    {
        if(patientRepository.existsByEmail(patientRequestDTO.getEmail()))
        {
            throw new EmailAlreadyExistsException("A patient with this email already exists" + patientRequestDTO.getEmail());
        }
        Patient newPatient = patientRepository.save(PatientMapper.toModel(patientRequestDTO));
        return PatientMapper.toDTO(newPatient);
    }

    public PatientResponseDTO updatePatient(UUID id,PatientRequestDTO patientRequestDTO)
    {
        Patient patient = patRepo.findById(id).orElseThrow(()-> new PatientNotFoundException("Patient not found by id."+ id));

        if(patientRepository.existsByEmailAndIdNot(patientRequestDTO.getEmail(),id))
        {
            throw new EmailAlreadyExistsException("A patient with this email already exists" + patientRequestDTO.getEmail());
        }

        patient.setName(patientRequestDTO.getName());
        patient.setAddress(patientRequestDTO.getAddress());
        patient.setEmail(patientRequestDTO.getEmail());
        patient.setDateOfBirth(LocalDate.parse(patientRequestDTO.getDateOfBirth()));

        Patient updatedPatient = patientRepository.save(patient);
        return PatientMapper.toDTO(updatedPatient);

    }

    public void deletePatient(UUID id)
    {
        patientRepository.deleteById(id);

    }
}
