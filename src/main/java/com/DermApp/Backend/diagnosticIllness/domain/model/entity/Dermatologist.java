package com.DermApp.Backend.diagnosticIllness.domain.model.entity;

import com.DermApp.Backend.shared.domain.model.AuditModel;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@With
@Entity
@Table(name = "dermatologists")
public class Dermatologist extends AuditModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotBlank
    @Column(unique = true)
    @Size(max = 60)
    private String name;

    private int age;

    @Size(max = 60)
    private String password;

    @Size(max = 240)
    private String address;

    @Size(max = 400)
    private String description;

}
