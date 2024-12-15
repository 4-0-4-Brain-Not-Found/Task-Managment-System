package _4.example.taskManagement.dto;

public class UserDTO {
    private String username;
    private String email;
    private String phoneNo;

    // Constructor
    public UserDTO(String username, String email, String phoneNo) {
        this.username = username;
        this.email = email;
        this.phoneNo = phoneNo;
    }

    // Getters and setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }
}


