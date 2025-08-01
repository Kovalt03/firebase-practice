package std.kovalt03.controller;

import org.springframework.web.bind.annotation.*;
import std.kovalt03.dto.MessageRequest;
@RestController
public class LoginController {

    @PostMapping("/login")
    public String postMessage(@RequestBody MessageRequest request) {
        return "id: " + request.getUsername() + ", password: " + request.getPassword();
    }
}
