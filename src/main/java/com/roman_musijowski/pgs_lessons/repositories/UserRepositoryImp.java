package com.roman_musijowski.pgs_lessons.repositories;

import com.roman_musijowski.pgs_lessons.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepositoryImp extends JpaRepository<User, Long> {

    Optional<User> findByUserName(String userName);
}
