package com.example.gradebook.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.Entity;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "grades", schema = "gradebook_db")
public class Grade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double grade;

    @ManyToOne(fetch = FetchType.EAGER)
        @JoinColumn(name = "category_id")
        private Category category;

    @ManyToOne(fetch = FetchType.EAGER)
        @JoinColumn(name = "subject_id")
        private Subject subject;

    @ManyToOne(fetch = FetchType.EAGER)
        @JoinColumn(name = "user_id")
        private User user;

    public Grade(){
    }

    public Grade(Long id, Double grade, Category category, Subject subject, User user) {
        this.id = id;
        this.grade = grade;
        this.category = category;
        this.subject = subject;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getGrade() {
        return grade;
    }

    public void setGrade(Double grade) {
        this.grade = grade;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
