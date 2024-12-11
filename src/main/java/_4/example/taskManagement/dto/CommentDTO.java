package _4.example.taskManagement.dto;

import java.util.Date;

public class CommentDTO {

    private Long id;
    private String content;
    private Date createdAt;
    private Long taskId;
    private Long assignedUserId;

    public CommentDTO(Long id, String content, Date createdAt, Long taskId, Long assignedUserId) {
        this.id = id;
        this.content = content;
        this.createdAt = createdAt;
        this.taskId = taskId;
        this.assignedUserId = assignedUserId;
    }

    // Getter ve Setter metodları
    public Long getId() {return id;}

    public void setId(Long id) {this.id = id;}

    public String getContent() {return content;}

    public void setContent(String content) {this.content = content;}

    public Date getCreatedAt() {return createdAt;}

    public void setCreatedAt(Date createdAt) {this.createdAt = createdAt;}

    public Long getTaskId() {return taskId;}

    public void setTaskId(Long taskId) {this.taskId = taskId;}

    public Long getAssignedUserId() {return assignedUserId;}

    public void setAssignedUserId(Long assignedUserId) {this.assignedUserId = assignedUserId;}
}
