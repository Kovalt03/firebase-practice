package std.kovalt03.spring;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("https://kovalt03.web.app", "https://kovalt03.firebaseapp.com") // Firebase 주소
                .allowedMethods("*")
                .allowCredentials(true)
                .allowedHeaders("*");
    }
}

