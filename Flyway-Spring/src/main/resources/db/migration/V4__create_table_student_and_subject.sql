create table if not exists student_subject
(
    subject_id bigint,
    student_id bigint,
    FOREIGN KEY (subject_id) REFERENCES subject (subject_id),
    FOREIGN KEY (student_id) REFERENCES student (student_id)
);

insert into student_subject (subject_id, student_id)
values (1, 1),
       (1, 2),
       (1, 3),
       (2, 1),
       (3, 2),
       (4, 2);