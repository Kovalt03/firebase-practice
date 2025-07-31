package std.kovalt03.spring;

import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
public class HelloController {

    @GetMapping("/api/hello")
    public String getMessage(@RequestParam String name) {
        return "Hello " + name + "from Spring Boot!";
    }
}
