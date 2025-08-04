package std.kovalt03.spring;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin(origins = "https://kovalt03.web.app", allowCredentials = "true")
public class CheckAuth {

    @GetMapping("/check-auth")
    public ResponseEntity<Map<String, String>> getUser(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("sessionId".equals(cookie.getName())) {
                    Map<String, String> authData = new HashMap<>();
                    String sessionId = cookie.getValue();
                    //  Map<String, String> userData = getUserData(sessionId, sessionData);
                    Map<String, String> userData = new HashMap<>();
                    userData.put("role", "user");
                    userData.put("username", "testUser");

                    authData.put("role", userData.get("role"));
                    authData.put("username", userData.get("username"));
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