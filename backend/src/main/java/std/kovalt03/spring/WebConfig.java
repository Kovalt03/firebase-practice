package std.kovalt03.spring;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("https://your-project-id.web.app", "https://your-project-id.firebaseapp.com") // Firebase 주소
                .allowedMethods("*")
                .allowedHeaders("*");
    }
}

