package _4.example.taskManagement.dto;

public class LoginDTO {

    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LoginDTO(String password, String username) {
        this.password = password;
        this.username = username;
    }


}
