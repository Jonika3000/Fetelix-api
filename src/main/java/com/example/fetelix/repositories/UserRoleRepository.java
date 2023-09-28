package com.example.fetelix.repositories;

import com.example.fetelix.models.User;
import com.example.fetelix.models.UserRole;
import com.example.fetelix.models.UserRolePK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, UserRolePK> {
    List<UserRole> findByUser(User User);
}