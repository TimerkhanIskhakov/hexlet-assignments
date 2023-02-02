package exercise;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeController {

    @GetMapping("/")
    public String getRootPage() {
        return "Welcome to Spring";
    }

    @GetMapping("/hello")
    public String getHelloPage(@RequestParam(required = false) String name) {
        return name == null || name.isEmpty() ? "Hello, World!" : String.format("Hello, %s!", name);
    }

}
