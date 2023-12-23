package com.mahindra.finance;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByName(String username);
    User findByEmailAndPassword(String email, String password);
    User findByEmail(String email);
}