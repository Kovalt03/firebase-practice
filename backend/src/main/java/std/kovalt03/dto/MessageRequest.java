package std.kovalt03.dto;

public class MessageRequest {
    private String username;
    private String hashedPassword;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return hashedPassword;
    }

    public void setPassWord(String hashedPassword) {
        this.hashedPassword = hashedPassword;
    }
}