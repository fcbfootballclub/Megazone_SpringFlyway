package com.example.flywayspring.Student.Service;

import com.example.flywayspring.Student.Entity.Student;
import com.example.flywayspring.Student.Repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService{
    @Autowired private StudentRepository studentRepository;
    @Override
    public List<Student> getAllStudent() {
        return studentRepository.findAll();
    }

    @Override
    public Optional<Student> findStudentById(Long id) {
        return studentRepository.findById(id);
    }

    @Override
    public int addStudent(Student student) {
        if(!studentRepository.findAll().contains(student)) {
            studentRepository.save(student);
            return 1;
        }
        return 0;
    }

    @Override
    public int updateStudent(Student student, Long id) {
        Optional<Student> found = studentRepository.findById(id);
        if(found.isPresent()) {
            found.get().setStudentName(student.getStudentName());
            found.get().setStudentEmail(student.getStudentEmail());
            studentRepository.save(found.get());
            return 1;
        }
        return 0;
    }

    @Override
    public int deleteStudentById(Long id) {
        Optional<Student> student = studentRepository.findById(id);
        if(student.isPresent()) {
            studentRepository.delete(student.get());
            return 1;
        }
        return 0;
    }
}
