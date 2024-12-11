package _4.example.taskManagement.entities;

import _4.example.taskManagement.dto.TaskDTO;
import _4.example.taskManagement.entities.users.User;
import _4.example.taskManagement.enums.TaskStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.*;

import java.util.Date;

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


}
