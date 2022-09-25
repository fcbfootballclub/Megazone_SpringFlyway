package com.example.flywayspring.Class.Repository;

import com.example.flywayspring.Class.Entity.ClassName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassNameRepository extends JpaRepository<ClassName, Long> {

}
