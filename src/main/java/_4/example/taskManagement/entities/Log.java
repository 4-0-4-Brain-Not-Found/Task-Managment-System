package _4.example.taskManagement.entities;

import jakarta.persistence.*;
import java.util.Date;

@Entity
public class Log {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String message;  // Log mesajı
    @Column(nullable = false)
    private String username; // Log ile ilişkili kullanıcı
    @Column(nullable = false)
    private Date timestamp;  // İşlem zamanı

    // Parametresiz yapıcı (Default Constructor)
    public Log() {
    }

    // Parametreli yapıcı
    public Log(String username, String message, Date timestamp) {
        this.username = username;
        this.message=message;
        this.timestamp = timestamp;
    }

    // Getters ve Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }



    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }
}
