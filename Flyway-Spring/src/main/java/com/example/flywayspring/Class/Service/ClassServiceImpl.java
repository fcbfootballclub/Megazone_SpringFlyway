package com.example.flywayspring.Class.Service;

import com.example.flywayspring.Class.Entity.ClassName;
import com.example.flywayspring.Class.Repository.ClassNameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClassServiceImpl implements ClassService{
    @Autowired private ClassNameRepository classNameRepository;

    @Override
    public List<ClassName> getAllClass() {
        return classNameRepository.findAll();
    }

    @Override
    public Optional<ClassName> getClassById(Long id) {
        return classNameRepository.findById(id);
    }

    @Override
    public int addClass(ClassName className) {
        if(!classNameRepository.findAll().contains(className)) {
            classNameRepository.save(className);
            return 1;
        }
        return 0;
    }

    @Override
    public int updateClass(Long classId, ClassName className) {
        Optional<ClassName> found = classNameRepository.findById(classId);
        if(found.isPresent()){
            found.get().setClassName(className.getClassName());
            found.get().setGrade(className.getGrade());
            classNameRepository.save(found.get());
            return 1;
        }
        return 0;
    }

    @Override
    public int deleteClass(Long id) {
        Optional<ClassName> found = classNameRepository.findById(id);
        if(found.isPresent()){
            classNameRepository.delete(found.get());
            return 1;
        }
        return 0;
    }
}
