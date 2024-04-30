package com.simplelearn.sportyshoes.repository;

import com.simplelearn.sportyshoes.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
}
