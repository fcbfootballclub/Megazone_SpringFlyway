package com.example.flywayspring.Subject.Entity;

import com.example.flywayspring.Student.Entity.Student;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "subject")
@Getter
@Setter
@Builder
@Accessors(chain = true)
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "subject_id")
    private Long subjectId;
    @Column(name = "subject_title", nullable = false, unique = true)
    private String subjectTitle;

    @ManyToMany
    @JsonIgnore
    @JoinTable(name = "student_subject",
            joinColumns = @JoinColumn(name = "subject_id"),
            inverseJoinColumns = @JoinColumn(name = "student_id"))
    private Collection<Student> studentsList;

}
