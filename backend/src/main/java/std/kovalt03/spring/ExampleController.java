package std.kovalt03.spring;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ExampleController {

    @GetMapping("/message")
    public String getMessage() {
        return "Hello from Spring Boot!";
    }
}
