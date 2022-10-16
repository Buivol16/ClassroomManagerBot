package ua.denis.dev.db.model.impl;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import ua.denis.dev.db.DBHandler;

@Entity(name = "user")
public class User {
    @Id
    @Column(name = "id")
    @Getter @Setter
    private long Id;
    @Column(name = "account_type")
    @Getter @Setter
    private String accountType;
    @Column(name = "name")
    @Getter @Setter
    private String name;


    public User() {
    }

    public User(long id, String accountType, String name) {
        Id = id;
        this.accountType = accountType;
        this.name = name;
    }

    public boolean isTeacher() {
        boolean answer = false;
        try {
            if (DBHandler.getInstance().getObject(getId(), getUser()).getAccountType().equals("Teacher")) answer = true;
        } catch (NullPointerException e) {

        }
        return answer;
    }
    public boolean isStudent() {
        boolean answer = false;
        try {
            if (DBHandler.getInstance().getObject(getId(), getUser()).getAccountType().equals("Student")) answer = true;
        } catch (NullPointerException e) {

        }
        return answer;
    }

    private User getUser(){
        return new User(getId(), getAccountType(), getName());
    }

}
