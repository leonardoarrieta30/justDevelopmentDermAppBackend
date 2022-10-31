package com.DermAppp.Backend.overview.service;

import com.DermAppp.Backend.overview.domain.model.entity.Dermatologist;
import com.DermAppp.Backend.overview.domain.persistence.DermatologistRepository;
import com.DermAppp.Backend.overview.domain.service.DermatologistService;
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
public class DermatologistServiceImpl implements DermatologistService {
    private static final String ENTITY = "Dermatologist";
    private final DermatologistRepository dermatologistRepository;

    private final Validator validator;

    public DermatologistServiceImpl(DermatologistRepository dermatologistRepository, Validator validator) {
        this.dermatologistRepository = dermatologistRepository;
        this.validator = validator;
    }


    @Override
    public List<Dermatologist> getAll() {
        return dermatologistRepository.findAll();
    }

    @Override
    public Page<Dermatologist> getAll(Pageable pageable) {
        return dermatologistRepository.findAll(pageable);
    }

    @Override
    public Dermatologist getById(Long dermatologistId) {
        return dermatologistRepository.findById(dermatologistId)
                .orElseThrow(() -> new ResourceNotFoundException(ENTITY,dermatologistId));
    }

    @Override
    public Dermatologist create(Dermatologist dermatologist) {
        Set<ConstraintViolation<Dermatologist>> violations = validator.validate(dermatologist);

        if(!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        Dermatologist dermatologistWithName = dermatologistRepository.findByName(dermatologist.getName());

        if(dermatologistWithName != null)
            throw new ResourceValidationException(ENTITY,
                    "A Dermatologist with the same name already exists.");

        return dermatologistRepository.save(dermatologist);

    }

    @Override
    public Dermatologist update(Long dermatologistId, Dermatologist request) {
        Set<ConstraintViolation<Dermatologist>> violations = validator.validate((request));

        if(!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        Dermatologist dermatologistWithName = dermatologistRepository.findByName(request.getName());

        if(dermatologistWithName != null && !dermatologistWithName.getId().equals(dermatologistId))
            throw new ResourceValidationException(ENTITY,
                    "A Dermatologist with the same name already exists.");

        return dermatologistRepository.findById(dermatologistId).map(dermatologist ->
                        dermatologistRepository.save(
                                dermatologist.withName(request.getName())
                                        .withAge(request.getAge())
                                        .withAddress(request.getAddress())
                                        .withPassword(request.getPassword())
                                        .withDescription(request.getDescription())))
                .orElseThrow(() -> new ResourceNotFoundException(ENTITY, dermatologistId));
    }

    @Override
    public ResponseEntity<?> delete(Long dermatologistId) {
        return dermatologistRepository.findById(dermatologistId).map(dermatologist -> {
            dermatologistRepository.delete(dermatologist);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException(ENTITY, dermatologistId));
    }
}
