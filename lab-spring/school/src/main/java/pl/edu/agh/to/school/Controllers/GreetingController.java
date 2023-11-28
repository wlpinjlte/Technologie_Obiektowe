package pl.edu.agh.to.school.Controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GreetingController {
//    @GetMapping
//    public String greeting() {
//        return "Technologie obiektowe";
//    }
    @GetMapping
    public List<String> greeting() {
        return List.of("Technologie", "obiektowe");
    }
}
