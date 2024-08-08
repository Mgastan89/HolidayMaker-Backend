package com.finalproject.com.HolidayMaker.repository;

import com.finalproject.com.HolidayMaker.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
    User findByEmail(String email);
}
