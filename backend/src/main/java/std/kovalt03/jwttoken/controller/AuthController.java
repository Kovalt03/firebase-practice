package std.kovalt03.jwttoken.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import std.kovalt03.jwttoken.security.JwtUtil;
import std.kovalt03.jwttoken.config.SecurityConfig;
import java.util.Map;
import java.util.HashMap;
import std.kovalt03.jwttoken.dto.LoginRequest;
import std.kovalt03.jwttoken.dto.TokenPair;
import std.kovalt03.db.UserEntity;
import std.kovalt03.db.UserService;
import std.kovalt03.jwttoken.dto.JwtTokenResponse;


@RestController
@CrossOrigin(origins = "https://kovalt03.web.app", allowCredentials = "true")
public class AuthController {
    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        String username = request.getUsername();
        String password = request.getPassword();

        UserEntity user = userService.findByUsernameAndPassword(username, password);
        if (user == null) {
            return ResponseEntity.status(401).body("Invalid username or password");
        }

        //claims 생성
        Map<String, Object> claims = new HashMap<>();
        claims.put("role", user.getRole());
        claims.put("uid", user.getUid());
        TokenPair tokenPair = jwtUtil.generateToken(username, claims);

        String accessToken = tokenPair.getAccessToken();
        String refreshToken = tokenPair.getRefreshToken();

        return ResponseEntity.ok(new JwtTokenResponse(accessToken, refreshToken));
    }
}

