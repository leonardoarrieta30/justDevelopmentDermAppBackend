package com.DermApp.Backend.diagnosticIllness.resource;

import lombok.*;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class DermatologistResource {
    private Long id;
    private String name;
    private int age;
    private String address;
    private String password;
    private String description;
}
