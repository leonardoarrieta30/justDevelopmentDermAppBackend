package com.DermAppp.Backend.overview.api.rest;

import com.DermAppp.Backend.overview.domain.service.DermatologistService;
import com.DermAppp.Backend.overview.mapping.DematologistMapper;
import com.DermAppp.Backend.overview.resource.CreateDermatologistResource;
import com.DermAppp.Backend.overview.resource.DermatologistResource;
import com.DermAppp.Backend.overview.resource.UpdateDermatologistResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/dermatologists", produces = "application/json")
public class DermatologistsController {

    private final DermatologistService dermatologistService;

    private final DematologistMapper mapper;


    public DermatologistsController(DermatologistService dermatologistService, DematologistMapper mapper) {
        this.dermatologistService = dermatologistService;
        this.mapper = mapper;
    }
    @GetMapping
    public Page<DermatologistResource> getAllDermatologists(Pageable pageable) {
        return mapper.modelListPage(dermatologistService.getAll(), pageable);
    }


    @GetMapping("{dermatologistId}")
    public DermatologistResource getDermatologistById(@PathVariable Long dermatologistId){
        return mapper.toResource(dermatologistService.getById(dermatologistId));
    }

    @PostMapping
    public ResponseEntity<DermatologistResource> createDermatologist(@RequestBody CreateDermatologistResource resource){
        return new ResponseEntity<>(mapper.toResource(dermatologistService.create(mapper.toModel(resource))), HttpStatus.CREATED);
    }

    @PutMapping("{dermatologistId}")
    public DermatologistResource updateDermatologist(@PathVariable Long dermatologistId, @RequestBody UpdateDermatologistResource resource){
        return mapper.toResource(dermatologistService.update(dermatologistId,mapper.toModel(resource)));
    }


    @DeleteMapping("{dermatologistId}")
    public ResponseEntity<?> deleteDermatologist(@PathVariable Long dermatologistId){
        return dermatologistService.delete(dermatologistId);
    }

}
