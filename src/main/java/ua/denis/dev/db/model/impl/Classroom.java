package ua.denis.dev.db.model.impl;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import ua.denis.dev.db.model.Model;

@Entity(name = "classroom")
public class Classroom implements Model {
    @Id @Getter @Setter @Column(name = "id")
    private long id;
    @Getter @Setter @Column(name = "name")
    private String name;

    public Classroom() {
    }

    public Classroom(long id, String name) {
        this.id = id;
        this.name = name;
    }


}
