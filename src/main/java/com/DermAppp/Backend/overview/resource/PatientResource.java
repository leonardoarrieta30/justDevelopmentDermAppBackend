package com.DermAppp.Backend.overview.resource;

import lombok.*;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class PatientResource {
    private Long id;
    private String name;
    private int age;
    private String address;
    private String password;
    private String description;
}
