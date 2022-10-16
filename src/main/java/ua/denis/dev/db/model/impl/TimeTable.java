package ua.denis.dev.db.model.impl;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "timetable")
public class TimeTable {
    @Id
    @Getter @Setter
    @Column(name = "classroom_id")
    private long classroomId;

    @Getter @Setter @Column(name = "lessons_time")
    private String lessonsTime;

    public TimeTable() {
    }

    public TimeTable(long classroomId, String lessonsTime) {
        this.classroomId = classroomId;
        this.lessonsTime = lessonsTime;
    }


}
