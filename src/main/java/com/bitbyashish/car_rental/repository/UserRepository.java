package com.bitbyashish.car_rental.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bitbyashish.car_rental.entity.User;
import com.bitbyashish.car_rental.entity.User.Role;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    List<User> findByRole(Role role);
    
}