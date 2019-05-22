package com.roman_musijowski.pgs_lessons.services.serviccRestFull;

import com.roman_musijowski.pgs_lessons.models.security.Role;
import com.roman_musijowski.pgs_lessons.repositories.RoleRepositoryImp;
import com.roman_musijowski.pgs_lessons.services.RoleService;
import com.roman_musijowski.pgs_lessons.util.dto.RoleDTO;
import com.roman_musijowski.pgs_lessons.util.mappers.RoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class RoleServiceRestImp implements RoleService {

    private final RoleRepositoryImp roleRepositoryImp;
    private final RoleMapper roleMapper;

    @Autowired
    public RoleServiceRestImp(RoleRepositoryImp roleRepositoryImp, RoleMapper roleMapper) {
        this.roleRepositoryImp = roleRepositoryImp;
        this.roleMapper = roleMapper;
    }


    @Override
    @Transactional(readOnly = true)
    public List<RoleDTO> listAll() {
        return roleRepositoryImp
                .findAll()
                .stream()
                .map(role -> {
                    RoleDTO roleDTO= roleMapper.roleToRoleDTO(role);
                    return roleDTO;
                })
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public RoleDTO getById(Long id) {
        return null;
    }

    @Override
    public RoleDTO createNew(RoleDTO object) {

        return saveAndReturnDTO(roleMapper.roleDTOToRole(object));
    }

    @Override
    public RoleDTO putByDTO(Long id, RoleDTO object) {

        Role role = roleMapper.roleDTOToRole(object);
        role.setRoleId(Math.toIntExact(id));

        return saveAndReturnDTO(role);
    }

    @Override
    public RoleDTO patchByDTO(Long id, RoleDTO object) {
        return null;
    }

    @Override
    public void deleteById(Long id) {
    }

    @Override
    public RoleDTO saveAndReturnDTO(Role role) {
        Role savedRole = roleRepositoryImp.save(role);

        RoleDTO returnDTO = roleMapper.roleToRoleDTO(savedRole);
        return returnDTO;
    }
}
