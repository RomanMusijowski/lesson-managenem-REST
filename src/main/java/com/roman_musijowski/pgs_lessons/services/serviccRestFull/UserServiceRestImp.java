package com.roman_musijowski.pgs_lessons.services.serviccRestFull;

import com.roman_musijowski.pgs_lessons.controllers.UserController;
import com.roman_musijowski.pgs_lessons.models.User;
import com.roman_musijowski.pgs_lessons.models.security.Role;
import com.roman_musijowski.pgs_lessons.repositories.UserRepositoryImp;
import com.roman_musijowski.pgs_lessons.security.services.EncryptionService;
import com.roman_musijowski.pgs_lessons.services.RoleService;
import com.roman_musijowski.pgs_lessons.services.UserService;
import com.roman_musijowski.pgs_lessons.services.exeptions.ResourceNotFoundException;
import com.roman_musijowski.pgs_lessons.util.dto.UserDTO;
import com.roman_musijowski.pgs_lessons.util.mappers.RoleMapper;
import com.roman_musijowski.pgs_lessons.util.mappers.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserServiceRestImp implements UserService {

    private final EncryptionService encryptionService;
    private final UserRepositoryImp userRepositoryImp;
    private final RoleService roleService;
    private final UserMapper userMapper;
    private final RoleMapper roleMapper;

    @Autowired
    public UserServiceRestImp(EncryptionService encryptionService, UserRepositoryImp userRepositoryImp
            , RoleService roleService, UserMapper userMapper, RoleMapper roleMapper) {
        this.encryptionService = encryptionService;
        this.userRepositoryImp = userRepositoryImp;
        this.roleService = roleService;
        this.userMapper = userMapper;
        this.roleMapper = roleMapper;
    }



    @Override
    @Cacheable(cacheNames = "users", key = "#userName")
    @Transactional(readOnly = true)
    public User findByUserName(String userName) {
        return userRepositoryImp.findByUserName(userName)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with userId " + userName));
    }

    @Override
    @Cacheable(value = "users")
    @Transactional(readOnly = true)
    public List<UserDTO> listAll() {
        return userRepositoryImp
                .findAll()
                .stream()
                .map(user -> {
                    UserDTO userDTO = userMapper.userToUserDTO(user);
                    userDTO.setUserUrl(getUserUrl(user.getId()));
                    return userDTO;
                })
                .collect(Collectors.toList());
    }


    @Override
    @Cacheable(cacheNames = "users", key = "#id")
    @Transactional(readOnly = true)
    public UserDTO getById(Long id) {
        return userRepositoryImp.findById(id)
                .map(userMapper::userToUserDTO)
                .map(userDTO -> {
                    userDTO.setUserUrl(getUserUrl(id));
                    return userDTO;
                })
                .orElseThrow(ResourceNotFoundException::new);
    }


    @Override
    public UserDTO createNew(UserDTO userDTO) {
        //get list of users objects
        List<Role> roles = roleService.listAll()
                .stream()
                .map(roleDTO -> {
                    Role role = roleMapper.roleDTOToRole(roleDTO);
                    return role;
                }).collect(Collectors.toList());


        User newUser = userMapper.userDTOToUser(userDTO);
        if (newUser.getId() == null){
            roles.forEach(role -> {

                //Assign user to ADMIN role
                if(role.getRole().equalsIgnoreCase("ADMIN")){
                    if (newUser.getUserName().equals("admin@gmail.com")) {
                        newUser.addRole(role);
                    }
                }

                //Assign user to STUDENT role
                if(role.getRole().equals("STUDENT")){
                    newUser.addRole(role);
                }
            });
        }

        return saveAndReturnDTO(newUser);
    }


    @Caching(evict =
            {@CacheEvict(cacheNames = "lessons", allEntries = true),
             @CacheEvict(cacheNames = "users", allEntries = true)})
    public UserDTO saveAndReturnDTO(User user) {

        if(user.getPassword() != null){
            user.setEncryptedPassword(encryptionService.encryptString(user.getPassword()));
        }
        User savedUser = userRepositoryImp.save(user);

        UserDTO returnDto = userMapper.userToUserDTO(savedUser);
        returnDto.setUserUrl(getUserUrl(savedUser.getId()));

        return returnDto;
    }

    @Override
    @CacheEvict(cacheNames = "users", allEntries = true)
    public UserDTO putByDTO(Long id, UserDTO userDTO) {

        User user = userMapper.userDTOToUser(userDTO);
        user.setId(id);

        return saveAndReturnDTO(user);
    }

    @Override
    @CacheEvict(cacheNames = "users", allEntries = true)
    public UserDTO patchByDTO(Long id, UserDTO userDTO) {
        return userRepositoryImp.findById(id).map(user -> {

            if(userDTO.getName() != null){
                user.setName(userDTO.getName());
            }

            if(userDTO.getSurName() != null){
                user.setSurName(userDTO.getSurName());
            }

            if(userDTO.getUserName() != null){
                user.setUserName(userDTO.getUserName());
            }
            if(userDTO.getYearOfStudies() != null){
                user.setYearOfStudies(userDTO.getYearOfStudies());
            }
            if(userDTO.getFieldOfStudy() != null){
                user.setFieldOfStudy(userDTO.getFieldOfStudy());
            }
            if(userDTO.getIndex() != null){
                user.setIndex(userDTO.getIndex());
            }

            UserDTO returnDto = userMapper.userToUserDTO(userRepositoryImp.save(user));

            returnDto.setUserUrl(getUserUrl(id));
            return returnDto;

        }).orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    @CacheEvict(cacheNames = "users", allEntries = true)
    public void deleteById(Long id) {
        userRepositoryImp.deleteById(id);
    }

    private String getUserUrl(Long id) {
        return UserController.BASE_URL + "/" + id;
    }
}
