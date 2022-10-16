package ua.denis.dev.db.model.impl;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import ua.denis.dev.bot.utils.Date;

@Entity(name = "homework")
public class Homework {
    @Id @Getter @Setter @Column(name = "classroom_id")
    private long classroomId;
    @Getter @Setter @Column(name = "subject_name")
    private String subject_name;
    @Getter @Setter @Column(name = "task")
    private String task;
    @Getter @Setter @Column(name = "date")
    private Date date;

    public Homework() {
    }

    public Homework(long classroomId, String subject_name, String task, Date date) {
        this.classroomId = classroomId;
        this.subject_name = subject_name;
        this.task = task;
        this.date = date;
    }


}
