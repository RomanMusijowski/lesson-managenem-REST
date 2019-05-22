package com.roman_musijowski.pgs_lessons.services;

import com.roman_musijowski.pgs_lessons.models.security.Role;
import com.roman_musijowski.pgs_lessons.util.dto.RoleDTO;

public interface RoleService extends CRUDService<RoleDTO> {

    RoleDTO saveAndReturnDTO(Role role);
}
