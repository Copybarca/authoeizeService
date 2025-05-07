package org.example.authorizationservice.model;

import jakarta.persistence.*;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.util.Objects;

@Entity
@Table(schema = "auth_service", name = "user")
@Component()
@Scope("prototype")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "secondName")
    private String secondName;
    @Column(name = "birthday")
    private Date birthday;
    @Column(name = "email")
    private String email;
    @Column(name = "password")
    private String password;

    @Deprecated
    public User() {}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(name, user.name) && Objects.equals(secondName, user.secondName) && Objects.equals(birthday, user.birthday) && Objects.equals(email, user.email) && Objects.equals(password, user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, secondName, birthday, email, password);
    }
    public User(String password, String email) {
        this.password = password;
        this.email = email;
    }
    public User(String password, String email, Date birthday, String secondName, String name, Long id) {
        this.password = password;
        this.email = email;
        this.birthday = birthday;
        this.secondName = secondName;
        this.name = name;
        this.id = id;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSecondName() {
        return secondName;
    }

    public Date getBirthday() {
        return birthday;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }



}
