package com.DermApp.Backend.overview.api.rest;

import com.DermApp.Backend.overview.domain.service.PatientService;
import com.DermApp.Backend.overview.mapping.PatientMapper;
import com.DermApp.Backend.overview.resource.CreatePatientResource;
import com.DermApp.Backend.overview.resource.PatientResource;
import com.DermApp.Backend.overview.resource.UpdatePatientResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "api/v1/patients", produces = "application/json")
public class PatientsController {

    public final PatientService patientService;
    private final PatientMapper mapper;


    public PatientsController(PatientService patientService, PatientMapper mapper) {
        this.patientService = patientService;
        this.mapper = mapper;
    }

    @GetMapping
    public Page<PatientResource> getAllPatients(Pageable pageable) {
        return mapper.modelListPage(patientService.getAll(), pageable);
    }

    @GetMapping("{patientId}")
    public PatientResource getPatientById(@PathVariable Long patientId){
        return mapper.toResource(patientService.getById(patientId));
    }

    @PostMapping
    public ResponseEntity<PatientResource> createPatient(@RequestBody CreatePatientResource resource){
        return new ResponseEntity<>(mapper.toResource(patientService.create(mapper.toModel(resource))), HttpStatus.CREATED);
    }

    @PutMapping("{patientId}")
    public PatientResource updatePatient(@PathVariable Long patientId, @RequestBody UpdatePatientResource resource){
        return mapper.toResource(patientService.update(patientId, mapper.toModel(resource)));
    }

    @DeleteMapping("{patientId}")
    public ResponseEntity<?> deletePatient(@PathVariable Long patientId) {
        return patientService.delete(patientId);
    }

}
