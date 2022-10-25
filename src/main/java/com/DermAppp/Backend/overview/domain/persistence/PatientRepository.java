package com.DermAppp.Backend.overview.domain.persistence;

import com.DermAppp.Backend.overview.domain.model.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
    List<Patient> findAllByAge(int age);

    Patient findByName(String name);
}
