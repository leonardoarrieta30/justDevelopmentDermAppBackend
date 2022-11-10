package com.DermApp.Backend.security.domain.service;

import com.DermApp.Backend.security.domain.model.entity.Role;

import java.util.List;

public interface RoleService {
    void seed();
    List<Role> getAll();
}
