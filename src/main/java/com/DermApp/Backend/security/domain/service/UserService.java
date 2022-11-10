package com.DermApp.Backend.security.domain.service;

import com.DermApp.Backend.security.domain.model.entity.User;
import com.DermApp.Backend.security.domain.service.communication.AuthenticateRequest;
import com.DermApp.Backend.security.domain.service.communication.RegisterRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    ResponseEntity<?> authenticate(AuthenticateRequest request);
    ResponseEntity<?> register(RegisterRequest request);
    List<User> getAll();
}
