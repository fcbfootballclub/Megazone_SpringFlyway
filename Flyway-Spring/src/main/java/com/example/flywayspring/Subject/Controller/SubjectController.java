package com.example.flywayspring.Subject.Controller;

import com.example.flywayspring.Subject.Entity.Subject;
import com.example.flywayspring.Subject.Service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping(path = "subject")
public class SubjectController {
    private SubjectService subjectService;

    @Autowired
    public SubjectController(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @GetMapping(path = "")
    public ResponseEntity<?> getAllSubject() {
        return ResponseEntity.ok(subjectService.getAll());
    }
}
