package com.DermApp.Backend.security.resource;


import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@With
public class AuthenticateResource {
    private Long id;
    private String username;
    private String email;

    //el cliente recibe coleccion de roles en string(recibe un objeto json)
    //nadie debe saber que existen que existen objetos rol
    private List<String> roles;

    //el cliente utiliza el token en multiples ocasiones, proporciona informacion sin la necesidad que
    //ingrese a cada rato usuario, contraseña
    private String token;
}
