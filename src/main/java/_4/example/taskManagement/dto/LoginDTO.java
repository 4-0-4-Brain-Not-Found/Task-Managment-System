package _4.example.taskManagement.dto;

public class LoginDTO {

    private Long id;
    private String username;
    private String password;

    // No-argument constructor (important for frameworks like Spring)
    public LoginDTO() {}

    // Constructor with id, username, and password
    public LoginDTO(Long id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    // Constructor without id (since id might not be necessary for login)
    public LoginDTO(String username, String password) {
        this.username = username;
        this.password = password;
    }

    // Getter and Setter for id
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    // Getter and Setter for username
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    // Getter and Setter for password
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
