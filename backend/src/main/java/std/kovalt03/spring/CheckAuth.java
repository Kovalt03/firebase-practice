package std.kovalt03.spring;

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
                    // const userData = getUserData(sessionId, sessionData);
                    const UserData = {
                        "role": "user",
                        "username": "testUser"
                    };
                    authData.put("role", UserData.role);
                    authData.put("username", UserData.username);
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