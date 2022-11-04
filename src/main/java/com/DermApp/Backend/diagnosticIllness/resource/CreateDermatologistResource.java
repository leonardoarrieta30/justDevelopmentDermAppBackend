package com.DermApp.Backend.diagnosticIllness.resource;

import lombok.*;

import javax.validation.constraints.*;

@Getter
@Setter
@With
@AllArgsConstructor
@NoArgsConstructor
public class CreateDermatologistResource {

    @NotNull
    @NotBlank
    @Size(max = 60)
    private String name;

    @Max(70)
    @Min(18)
    private int age;

    @Size(max = 60)
    private String password;

    @Size(max = 240)
    private String address;

    @Size(max = 400)
    private String description;
}
