package com.example.flywayspring.Student.Controller;

import com.example.flywayspring.Student.Entity.Student;
import com.example.flywayspring.Student.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/student")
@CrossOrigin
public class StudentController {
    @Autowired private StudentService studentService;

    @GetMapping(path = "")
    public ResponseEntity<List<Student>> getAllStudent() {
        return ResponseEntity.ok()
                .body(studentService.getAllStudent());
    }

    @GetMapping(path = "{studentID}")
    public ResponseEntity<Student> getStudentById(@PathVariable Long studentID) {
        Optional<Student> found = studentService.findStudentById(studentID);
        return found.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping(path = "")
    public ResponseEntity<Student> addStudent(@RequestBody Student student) {
        int done = studentService.addStudent(student);
        return done == 1? ResponseEntity.accepted().body(student) : ResponseEntity.badRequest().build();
    }

    @PutMapping(path = "{studentId}")
    public ResponseEntity<Student> updateStudentById(@PathVariable Long studentId, @RequestBody Student student) {
        int done = studentService.updateStudent(student, studentId);
        return done == 1? ResponseEntity.ok(student) : ResponseEntity.notFound().build();
    }

    @DeleteMapping(path = "{studentiD}")
    public ResponseEntity<Student> deleteStudentById(@PathVariable Long id) {
        int done = studentService.deleteStudentById(id);
        return done == 1? ResponseEntity.accepted().build() : ResponseEntity.notFound().build();
    }
}
