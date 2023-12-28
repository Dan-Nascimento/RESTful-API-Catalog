package mb.dabm.servcatapi.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.Data;
import mb.dabm.servcatapi.exception.EntityNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import java.util.Properties;

@RestController
@RequestMapping(value ="api", produces = "application/json")
@Data
@AllArgsConstructor
@Tag(name = "WelcomeController Para verificações da API SERVCAT")
public class WelcomeController {

    @GetMapping({"/", ""})
    @ResponseBody
    public String status() throws EntityNotFoundException {
        return "API SERVCAT com Spring Boot";
    }

    /**
     *
     * @return
     * URL: https://www.javaguides.net/2023/04/spring-security-basic-authentication.html
     */
    @GetMapping({"/info", "/info/"})
    public String info(Authentication authentication) throws EntityNotFoundException {
        String userName = authentication.getName();
        return "API SERVCAT com Spring Security In-memory Authentication - Welcome " + userName;
    }

    @GetMapping({"/status", "/status/"})
    public MappingJackson2JsonView status1() throws EntityNotFoundException {
        MappingJackson2JsonView view = new MappingJackson2JsonView();
        Properties props = new Properties();
        props.put("status", "UP");
        view.setAttributes(props);
        view.setPrettyPrint(true);
        return view;
    }

}
