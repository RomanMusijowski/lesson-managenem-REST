package com.roman_musijowski.pgs_lessons.util.converters;

import com.roman_musijowski.pgs_lessons.models.security.Role;
import com.roman_musijowski.pgs_lessons.util.dto.RoleDTO;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ToRoleDTO implements Converter<Role, RoleDTO> {
    @Override
    public RoleDTO convert(Role source) {

        RoleDTO roleDTO= new RoleDTO();
        roleDTO.setRoleId(source.getRoleId());
        roleDTO.setRole(source.getRole());

        return roleDTO;
    }
}
