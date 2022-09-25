package com.example.flywayspring.Student.Entity;

import com.example.flywayspring.Class.Entity.ClassName;
import com.example.flywayspring.Subject.Entity.Subject;
import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "student")
@Getter
@Setter
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_id")
    private Long studentId;
    @Column(name = "student_name", nullable = false)
    private String studentName;
    @Column(name = "student_email", nullable = false)
    private String studentEmail;

    @ManyToOne
    @JoinColumn(name = "class_id")
    @EqualsAndHashCode.Exclude
    private ClassName className;

    @ManyToMany(mappedBy = "studentsList")
    @EqualsAndHashCode.Exclude
    private Collection<Subject> subjectsList;
}
