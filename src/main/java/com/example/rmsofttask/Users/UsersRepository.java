package com.example.rmsofttask.Users;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<Users, String> {
    boolean existsByEmail(String userId);

    boolean existsByPhoneNumber(String userId);
}
