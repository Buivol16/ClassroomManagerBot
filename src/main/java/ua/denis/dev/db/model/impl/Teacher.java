package ua.denis.dev.db.model.impl;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "teacher")
public class Teacher {
    @Id
    @Column(name = "id")
    @Getter
    @Setter
    private long Id;
    @Column(name = "classroom_id")
    @Getter @Setter
    private String classroomId;

    public Teacher() {
    }

    public Teacher(long id, String classroomId) {
        Id = id;
        this.classroomId = classroomId;
    }

    public boolean hasClassroom(){
        boolean answer = false;
        if (!getClassroomId().isBlank()) answer = true;
        return answer;
    }
}
