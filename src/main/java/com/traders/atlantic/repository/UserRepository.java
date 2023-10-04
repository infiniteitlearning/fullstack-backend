package com.traders.atlantic.repository;

import com.traders.atlantic.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
