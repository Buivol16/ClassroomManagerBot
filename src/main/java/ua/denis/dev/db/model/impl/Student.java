package ua.denis.dev.db.model.impl;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "student")
public class Student {
    @Id @Getter @Setter @Column(name = "student_id")
    private long studentId;
    @Getter @Setter @Column(name = "classroom_id")
    private long classroomId;

    public Student() {
    }

    public Student(long studentId, long classroomId) {
        this.studentId = studentId;
        this.classroomId = classroomId;
    }
}
