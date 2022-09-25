package com.example.flywayspring.Subject.Service;

import com.example.flywayspring.Subject.Entity.Subject;

import java.util.List;
import java.util.Optional;

public interface SubjectService {
    public List<Subject> getAll();
    public Optional<Subject> getSubjectById(long subjectId);
    public int addSubject(Subject subject);
    public int updateSubject(Long subjectId, Subject subject);
    public int deleteSubjectById(Long subjectId);
}
