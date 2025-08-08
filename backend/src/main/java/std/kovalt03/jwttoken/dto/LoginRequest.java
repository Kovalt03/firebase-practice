package std.kovalt03.jwttoken.dto;

import lombok.Data;

@Data
public class LoginRequest {
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

    public void setPassWord(String password) {
        this.password = password;
    }
}