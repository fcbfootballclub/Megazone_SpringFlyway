package com.example.flywayspring.Class.Service;

import com.example.flywayspring.Class.Entity.ClassName;

import java.util.List;
import java.util.Optional;

public interface ClassService {
    public List<ClassName> getAllClass();
    public Optional<ClassName> getClassById(Long id);

    public int addClass(ClassName className);
    public int updateClass(Long classId, ClassName className);
    public int deleteClass(Long id);
}
