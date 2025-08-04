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
    public ResponseEntity<String> getUser(HttpServletRequest request) {
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

                    authData.put("role", UserData.get("role"));
                    authData.put("username", UserData.get("username"));
                    return ResponseEntity.ok(authData);
                }
            }
        }
        return ResponseEntity.status(401).body({
            "error": "Unauthorized",
            "message": "User is not authenticated"
        });
    }
}