package _4.example.taskManagement.entities;


import _4.example.taskManagement.entities.users.User;
import _4.example.taskManagement.enums.TaskStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.persistence.CascadeType;
import jakarta.persistence.ForeignKey;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.*;

import java.util.*;

@Entity
@Data
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String title;

    @Column(nullable = false, length = 500)
    private String description;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dueDate;

    @Enumerated(EnumType.STRING)
    private TaskStatus taskStatus;


    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "assigned_user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private User assignedUser;


    public Long getId() {return id;}

    public String getTitle() {return title; }
    public String getDescription() {return description;}
    public User getAssignedUser() {return assignedUser;}
    public Date getDueDate() {return dueDate;}
    public TaskStatus getTaskStatus() {return taskStatus; }

    public void setTitle(String title) {this.title = title;}
    public void setDescription(String description) {this.description = description;}
    public void setDueDate(Date dueDate) {this.dueDate =dueDate;}
    public void setTaskStatus(TaskStatus taskStatus) {this.taskStatus=taskStatus;}
    public void setAssignedUser(User assignedUser) {this.assignedUser=assignedUser;}

}
