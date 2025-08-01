package std.kovalt03.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import std.kovalt03.dto.MessageRequest;

@RestController
@CrossOrigin(origins = "https://kovalt03.web.app", allowCredentials = "true")
public class LoginController {

    @PostMapping("/login")
    public ResponseEntity<String> Response(@RequestBody MessageRequest request) {
        String username = request.getUsername();
        String password = request.getPassword();

        HttpHeaders headers = new HttpHeaders();
        headers.add("Set-Cookie", "sessionId=1234; Path=/; HttpOnly; Secure; SameSite=None");

        String body = "id: " + request.getUsername() + ", password: " + request.getPassword();
        if (username.equals("admin") && password.equals("1234")) {
            return ResponseEntity.ok()
                    .headers(headers)
                    .body(body);
        }
        return ResponseEntity.status(401)
                .body("There is no user with this username and password");
    }
}
