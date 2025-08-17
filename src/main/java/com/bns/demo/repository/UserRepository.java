package com.bns.demo.repository;

import com.bns.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	Optional<User> findByEmailId(String emailId);
    boolean existsByEmailId(String emailId);
}