package _4.example.taskManagement.entities;

import _4.example.taskManagement.dto.CommentDTO;
import _4.example.taskManagement.entities.users.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.*;

import java.util.Date;

@Entity
@Data
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 500)
    private String content;

    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date createdAt;

    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name= "task_id", nullable = false)
    @JsonIgnore
    private Task task;

    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name= "user_id", nullable = false)
    @JsonIgnore
    private User assignedUser;


    public Long getId() {return id;}
    public String getContent() {return content;}
    public Date getCreatedAt() {return createdAt;}
    public Task getTask() {return task;}
    public User getAssignedUser() {return assignedUser;}
    public void setId(Long id) {this.id = id;}
    public void setContent(String content) {this.content = content;}
    public void setCreatedAt(Date createdAt) {this.createdAt = createdAt;}
    public void setTask(Task task) {this.task = task;}
    public void setAssignedUser(User assignedUser) {this.assignedUser = assignedUser;}
}

