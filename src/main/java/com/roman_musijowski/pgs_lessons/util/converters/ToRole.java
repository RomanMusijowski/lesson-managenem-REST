package com.roman_musijowski.pgs_lessons.util.converters;

import com.roman_musijowski.pgs_lessons.models.security.Role;
import com.roman_musijowski.pgs_lessons.util.dto.RoleDTO;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ToRole implements Converter<RoleDTO, Role> {
    @Override
    public Role convert(RoleDTO source) {

        Role role = new Role();
        role.setRoleId(source.getRoleId());
        role.setRole(source.getRole());

        return role;
    }
}
