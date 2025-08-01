package std.kovalt03.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseHeader;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import std.kovalt03.dto.MessageRequest;

@RestController
public class LoginController {

    @PostMapping("/login")
    public String Response(@RequestBody MessageRequest request) {
        String username = request.getUsername();
        String password = request.getPassword();

        HttpHeaders headers = new HttpHeaders();
        headers.add("Set-Cookie", "sessionId=; Path=/; HttpOnly; Secure; SameSite=Lax");

        String body "id: " + request.getUsername() + ", password: " + request.getPassword();
        if (username == "admin" && password == "1234") {
            return ResponseEntity.ok()
                    .headers(headers)
                    .body(body);
        }
        return ResponseEntity.ok()
                .headers(null)
                .body("There is no user with this username and password");
    }
}
