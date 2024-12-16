package _4.example.taskManagement.dto;

import java.util.Date;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class LogDTO {
    private String message;
    private String timestamp;
    private String username;

    public LogDTO(String message, Date timestamp, String username) {
        this.message = message;
        this.timestamp = convertToLocalDateTime(timestamp).toString(); // Convert Date to LocalDateTime
        this.username = username;
    }

    private LocalDateTime convertToLocalDateTime(Date date) {
        return date.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();
    }

    // Getters and Setters
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
