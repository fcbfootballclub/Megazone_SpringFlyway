package com.example.flywayspring.Student.Service;

import com.example.flywayspring.Student.Entity.Student;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public interface StudentService {
    public List<Student> getAllStudent();
    public Optional<Student> findStudentById(Long id);
    public int addStudent(Student student);
    public int updateStudent(Student student, Long id);
    public int deleteStudentById(Long id);
 }
