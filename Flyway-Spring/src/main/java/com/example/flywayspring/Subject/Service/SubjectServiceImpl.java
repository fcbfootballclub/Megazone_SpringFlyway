package com.example.flywayspring.Subject.Service;

import com.example.flywayspring.Subject.Entity.Subject;
import com.example.flywayspring.Subject.Repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SubjectServiceImpl implements SubjectService{
    @Autowired private SubjectRepository subjectRepository;

    @Override
    public List<Subject> getAll() {
        return subjectRepository.findAll();
    }

    @Override
    public Optional<Subject> getSubjectById(long subjectId) {
        return subjectRepository.findById(subjectId);
    }

    @Override
    public int addSubject(Subject subject) {
        if(!subjectRepository.findAll().contains(subject)) {
            return 0;
        }
        subjectRepository.save(subject);
        return 1;
    }

    @Override
    public int updateSubject(Long subjectId, Subject subject) {
        Optional<Subject> found = subjectRepository.findById(subjectId);
        if(found.isEmpty()) {
            return 0;
        }
        found.get().setSubjectTitle(subject.getSubjectTitle());
        return 1;
    }

    @Override
    public int deleteSubjectById(Long subjectId) {
        subjectRepository.deleteById(subjectId);
        return 1;
    }
}
