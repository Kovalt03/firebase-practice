package std.kovalt03.spring;
// package std.kovalt03.dto;
import org.springframework.web.bind.annotation.*;

@RestController
public class LoginController {

    @PostMapping("/login")
    public String postMessage(@RequestBody MessageRequest request) {
        return "id: " + request.getUsername() + ", password: " + request.getPassword();
    }
}
