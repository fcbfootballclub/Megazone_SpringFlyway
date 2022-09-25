package com.example.flywayspring.Class.Controller;

import com.example.flywayspring.Class.Entity.ClassName;
import com.example.flywayspring.Class.Service.ClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "class-name")
@CrossOrigin
public class ClassNameController {
    @Autowired private ClassService classService;

    @GetMapping(path = "")
    public ResponseEntity<List<ClassName>> getAllClassName() {
        return ResponseEntity.ok(classService.getAllClass());
    }

    @PostMapping(path = "")
    public ResponseEntity<?> addClass(@RequestBody ClassName className) {
        int done = classService.addClass(className);
        return done == 1? ResponseEntity.ok(className) : ResponseEntity.badRequest().body(className);
    }

    @PutMapping(path = "{classId}")
    public ResponseEntity<?> updateClass(@PathVariable Long classId, @RequestBody ClassName className) {
        int done = classService.updateClass(classId, className);
        return done == 1? ResponseEntity.ok(className) : ResponseEntity.notFound().build();
    }

    @DeleteMapping(path = "{classId}")
    public ResponseEntity<?> deleteClass(@PathVariable Long classId) {
        if(classId == null) return ResponseEntity.badRequest().build();
        int done = classService.deleteClass(classId);
        return done == 1 ? ResponseEntity.accepted().body("Delete successfully!") : ResponseEntity.notFound().build();
    }
}
