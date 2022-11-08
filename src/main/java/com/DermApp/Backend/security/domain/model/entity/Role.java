package com.DermApp.Backend.security.domain.model.entity;

import com.DermApp.Backend.security.domain.model.enumeration.Roles;
import com.DermApp.Backend.shared.domain.model.AuditModel;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@With
@Entity
@Table(name="roles")

public class Role extends AuditModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    //el nombre del rol va a estar acotado por ese enumeration
    private Roles name;


}
