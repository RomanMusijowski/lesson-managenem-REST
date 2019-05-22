package com.roman_musijowski.pgs_lessons.util.mappers;

import com.roman_musijowski.pgs_lessons.models.security.Role;
import com.roman_musijowski.pgs_lessons.util.dto.RoleDTO;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class RoleMapperTest {

    public static final String ROLE = "ROLE";
    public static final int ID = 1;

    RoleMapper roleMapper = RoleMapper.INSTANCE;

    @Test
    public void roleToRoleDTO() {
        //given
        Role role = new Role();
        role.setRole(ROLE);
        role.setRoleId(ID);

        //when
        RoleDTO roleDTO= roleMapper.roleToRoleDTO(role);

        //then
        assertEquals(Integer.valueOf(ID), roleDTO.getRoleId());
        assertEquals(ROLE, roleDTO.getRole());
    }

    @Test
    public void roleDTOToRole() {
        //given
        RoleDTO roleDTO = new RoleDTO();
        roleDTO.setRole(ROLE);
        roleDTO.setRoleId(ID);

        //when
        Role role = roleMapper.roleDTOToRole(roleDTO);

        //then
        assertEquals(Integer.valueOf(ID), role.getRoleId());
        assertEquals(ROLE, role.getRole());

    }
}