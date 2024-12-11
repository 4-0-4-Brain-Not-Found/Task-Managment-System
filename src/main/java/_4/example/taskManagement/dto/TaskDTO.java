package _4.example.taskManagement.dto;

import _4.example.taskManagement.enums.TaskStatus;
import java.util.Date;
import java.util.Set;

public class TaskDTO {

    private Long id;
    private String title;
    private String description;
    private Long assignedUserId;
    private Date dueDate;
    private TaskStatus taskStatus;
    private Set<CommentDTO> comments;

    public TaskDTO(Long id, String title, String description, Long assignedUserId, Date dueDate, TaskStatus taskStatus, Set<CommentDTO> comments) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.assignedUserId = assignedUserId;
        this.dueDate = dueDate;
        this.taskStatus = taskStatus;
        this.comments = comments;
    }

    // Getter ve Setter metodları
    public Long getId() {return id;}

    public void setId(Long id) {this.id = id;}

    public String getTitle() {return title;}

    public void setTitle(String title) {this.title = title;}

    public String getDescription() {return description;}

    public void setDescription(String description) {this.description = description;}

    public Long getAssignedUserId() {return assignedUserId;}

    public void setAssignedUserId(Long assignedUserId) {this.assignedUserId = assignedUserId;}

    public Date getDueDate() {return dueDate;}

    public void setDueDate(Date dueDate) {this.dueDate = dueDate;}

    public TaskStatus getTaskStatus() {return taskStatus;}

    public void setTaskStatus(TaskStatus taskStatus) {this.taskStatus = taskStatus;}

    public Set<CommentDTO> getComments() { return comments; }

    public void setComments(Set<CommentDTO> comments) { this.comments = comments; }

}
