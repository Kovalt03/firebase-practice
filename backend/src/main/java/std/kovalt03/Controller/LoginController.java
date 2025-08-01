package std.kovalt03.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.UUID;
import std.kovalt03.dto.MessageRequest;

@RestController
@CrossOrigin(origins = "https://kovalt03.web.app", allowCredentials = "true")
public class LoginController {

    @PostMapping("/login")
    public ResponseEntity<String> Response(@RequestBody MessageRequest request, HttpServletResponse response) {
        String username = request.getUsername();
        String password = request.getPassword();

        if ("admin".equals(username) && "1234".equals(password)) {
            String sessionId = UUID.randomUUID().toString();

            String cookieValue = "sessionId=" + sessionId + "; HttpOnly; Path=/; SameSite=None; Secure; Domain=kovalt03.web.app; Max-Age=3600";

            response.setHeader("Set-Cookie", cookieValue);

            return ResponseEntity.ok("Login Success");
        }
        return ResponseEntity.status(401)
                .body("There is no user with this username and password");
    }

    @GetMapping("/usr")
    public ResponseEntity<String> getUser(HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("sessionId".equals(cookie.getName())) {
                    String sessionId = cookie.getValue();
                    return ResponseEntity.ok("User is logged in with session ID: " + sessionId);
                }
            }
        }
        return ResponseEntity.status(401).body("No permission to access this resource. Please log in.");
    }
}
