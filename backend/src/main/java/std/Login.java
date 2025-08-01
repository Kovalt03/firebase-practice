package std.kovalt03.spring;

import org.springframework.web.bind.annotation.*;

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
        return password;
    }

    public void setPassWord(String passsword) {
        this.hashedPassword = password;
    }
}

@RestController
public class Login {

    @PostMapping("/login")
    public String postMessage(@RequestBody MessageRequest request) {
        return "id: " + request.getUsername() + ", password: " + request.getPassword();
    }
}
