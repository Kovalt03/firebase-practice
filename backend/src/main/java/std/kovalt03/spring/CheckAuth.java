package std.kovalt03.spring;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import std.kovalt03.db.GetUserDB;
import std.kovalt03.dto.MessageRequest;

@RestController
@CrossOrigin(origins = "https://kovalt03.web.app", allowCredentials = "true")
public class CheckAuth {

    @PostMapping("/check-auth")
    public ResponseEntity<Map<String, String>> getUser(HttpServletRequest request, @RequestBody MessageRequest requestBody) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("sessionId".equals(cookie.getName())) {
                    Map<String, String> authData = GetUserDB.getSession(requestBody.getUsername());
                    String sessionId = cookie.getValue();
                    if(!authData.get("sessionID").equals(sessionId)) {
                        Map<String, String> error = new HashMap<>();
                        error.put("error", "Unauthorized");
                        error.put("message", "Session ID does not match");
                        return ResponseEntity.status(401).body(error);
                    }
                    return ResponseEntity.ok(authData);
                }
            }
        }
        Map<String, String> error = new HashMap<>();
        error.put("error", "Unauthorized");
        error.put("message", "User is not authenticated");
        return ResponseEntity.status(401).body(error);
    }
}