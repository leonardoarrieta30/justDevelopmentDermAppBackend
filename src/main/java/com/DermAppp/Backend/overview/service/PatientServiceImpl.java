package com.DermAppp.Backend.overview.service;

import com.DermAppp.Backend.overview.domain.model.entity.Patient;
import com.DermAppp.Backend.overview.domain.persistence.PatientRepository;
import com.DermAppp.Backend.overview.domain.service.PatientService;
import com.DermAppp.Backend.shared.exception.ResourceNotFoundException;
import com.DermAppp.Backend.shared.exception.ResourceValidationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;

@Service
public class PatientServiceImpl implements PatientService {
    private static final String ENTITY = "Patient";
    private final PatientRepository patientRepository;

    private final Validator validator;

    public PatientServiceImpl(PatientRepository patientRepository, Validator validator) {
        this.patientRepository = patientRepository;
        this.validator = validator;
    }


    @Override
    public List<Patient> getAll() {
        return patientRepository.findAll();
    }

    @Override
    public Page<Patient> getAll(Pageable pageable) {
        return patientRepository.findAll(pageable);
    }

    @Override
    public Patient getById(Long patientId) {
        return patientRepository.findById(patientId)
                .orElseThrow(() -> new ResourceNotFoundException(ENTITY,patientId));
    }

    @Override
    public Patient create(Patient patient) {
        Set<ConstraintViolation<Patient>> violations = validator.validate(patient);

        if(!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        Patient patientWithName = patientRepository.findByName(patient.getName());

        if(patientWithName != null)
            throw new ResourceValidationException(ENTITY,
                    "A Patient with the same name already exists.");

        return patientRepository.save(patient);
    }

    @Override
    public Patient update(Long patientId, Patient request) {
        Set<ConstraintViolation<Patient>> violations = validator.validate((request));

        if(!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        Patient patientWithName = patientRepository.findByName(request.getName());

        if(patientWithName != null && !patientWithName.getId().equals(patientId))
            throw new ResourceValidationException(ENTITY,
                    "A Patient with the same name already exists.");

        return patientRepository.findById(patientId).map(patient ->
                        patientRepository.save(
                                patient.withName(request.getName())
                                        .withAge(request.getAge())
                                        .withAddress(request.getAddress())
                                        .withPassword(request.getPassword())
                                        .withDescription(request.getDescription())))
                .orElseThrow(() -> new ResourceNotFoundException(ENTITY, patientId));
    }

    @Override
    public ResponseEntity<?> delete(Long patientId) {
        return patientRepository.findById(patientId).map(patient -> {
            patientRepository.delete(patient);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException(ENTITY, patientId));
    }
}
