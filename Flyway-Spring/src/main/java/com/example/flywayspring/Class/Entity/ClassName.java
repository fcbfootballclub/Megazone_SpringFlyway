package com.example.flywayspring.Class.Entity;

import lombok.*;
import lombok.experimental.Accessors;
import javax.persistence.*;

@Entity
@Table(name = "class_name")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Accessors(chain = true)
@Builder
public class ClassName {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "class_id")
    private Long classId;
    @Column(name = "class_name", nullable = false)
    private String className;
    @Column(name = "grade", nullable = false)
    private Integer grade;
}
