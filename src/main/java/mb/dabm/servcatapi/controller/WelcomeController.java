package mb.dabm.servcatapi.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.Data;
import mb.dabm.servcatapi.exception.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
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

    @Autowired
    private Environment env;

// https://www.baeldung.com/spring-boot-build-properties
//    @Autowired
//    BuildProperties buildProperties;

    @ResponseBody
    @GetMapping({"/", ""})
    public  MappingJackson2JsonView status() throws EntityNotFoundException {
        MappingJackson2JsonView view = new MappingJackson2JsonView();
        Properties props = new Properties();
        props.put("status", "UP");
        props.put("title", env.getProperty("application.title"));
        props.put("app-name", env.getProperty("application.title"));
        props.put("version", env.getProperty("info.app.version"));
        props.put("java-version", env.getProperty("info.app.java.version"));
        props.put("encoding", env.getProperty("info.app.encoding"));
        props.put("profile", env.getProperty("spring.profiles.active"));
        props.put("description", env.getProperty("application.description"));
//        props.put("build-version", buildProperties.getVersion());
//        props.put("build-date", buildProperties.get("time"));
//        props.put("build-name", buildProperties.getName());
//        props.put("build-group", buildProperties.getGroup());
        view.setAttributes(props);
        view.setPrettyPrint(true);
        return view;
    }

    /**
     *
     * @return
     * URL: https://www.javaguides.net/2023/04/spring-security-basic-authentication.html
     */
    @GetMapping({"/test", "/test/"})
    public String info(Authentication authentication) throws EntityNotFoundException {
        String userName = authentication.getName();
        return "API SERVCAT com Spring Security In-memory Authentication - Welcome " + userName;
    }

}
