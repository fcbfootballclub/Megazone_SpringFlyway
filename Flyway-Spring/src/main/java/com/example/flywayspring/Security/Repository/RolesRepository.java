package com.example.flywayspring.Security.Repository;

import com.example.flywayspring.Security.Entity.RoleTitle;
import com.example.flywayspring.Security.Entity.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface RolesRepository extends JpaRepository<Roles, Long> {
    Optional<Roles> findRolesByRoleTitle(RoleTitle roleTitle);
}
