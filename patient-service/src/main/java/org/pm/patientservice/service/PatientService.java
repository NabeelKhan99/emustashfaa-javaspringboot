package org.pm.patientservice.service;

import org.pm.patientservice.dto.PatientRequestDTO;
import org.pm.patientservice.dto.PatientResponseDTO;
import org.pm.patientservice.mapper.PatientMapper;
import org.pm.patientservice.model.Patient;
import org.pm.patientservice.repository.PatientRepository;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service

public class PatientService {
    private final PatientRepository patientRepository;
    private PatientRepository patRepo; // Patient Repository object.?
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

    public PatientResponseDTO createPatient(PatientRequestDTO patientRequestDTO)
    {
        Patient newPatient = patientRepository.save(PatientMapper.toModel(patientRequestDTO));
        return PatientMapper.toDTO(newPatient);
    }
}
