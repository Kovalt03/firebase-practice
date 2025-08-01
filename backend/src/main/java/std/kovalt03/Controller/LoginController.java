package std.kovalt03.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
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
            Cookie cookie = new Cookie("sessionId", sessionId);
            cookie.setPath("/");
            cookie.setHttpOnly(true);
            cookie.setSecure(true);
            cookie.setSameSite("None");
            cookie.setMaxAge(60*60*3); // Set cookie expiration time to 1 hour
            cookie.setDomain("kovalt03.web.app");
            
            response.addCookie(cookie);

            return ResponseEntity.ok();
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
