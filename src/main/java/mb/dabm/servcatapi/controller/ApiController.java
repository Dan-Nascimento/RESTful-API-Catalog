package mb.dabm.servcatapi.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.Data;
import mb.dabm.servcatapi.exception.EntityNotFoundException;
import mb.dabm.servcatapi.helper.Helper;
import mb.dabm.servcatapi.model.ApiInfoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.info.BuildProperties;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import java.util.SortedMap;
import java.util.TreeMap;

@RestController
@RequestMapping(value = "api", produces = "application/json")
@Data
@AllArgsConstructor
@Tag(name = "WelcomeController Para verificações da API SERVCAT")
public class ApiController {

    @Autowired
    private Environment env;

    // https://www.baeldung.com/spring-boot-build-properties
    @Autowired
    BuildProperties buildProperties;

    @ResponseBody
    @GetMapping({"/", ""})
    public ResponseEntity<ApiInfoDto> status() throws EntityNotFoundException {
        String time = Helper.unixEpochToDate(Long.parseLong(buildProperties.get("time")));

        String[] m = new String[]
            {
                "[DAbM] - Diretoria de Abastecimento - Marinha do Brasil. ",
                "[DAbM-60] - Departamento de Informática. "
            };

        String[] e = new String[]
            {
                "CT Anders  - anders@marinha.mil.br - DAbM",
                "Wanderson Duarte - wanderson.duarte@eits.com.br - PROGNUS"
            };


        ApiInfoDto ApiInfoDto = new ApiInfoDto(
            env.getProperty("application.title"),
            "UP",
            env.getProperty("info.app.version"),
            env.getProperty("application.description"),
            env.getProperty("info.app.encoding"),
            env.getProperty("info.app.java.version"),
            buildProperties.get("spring-boot.version"),
            env.getProperty("spring.profiles.active"),
            time,
            buildProperties.getGroup(),
            m,
            e
        );

        return ResponseEntity.ok(ApiInfoDto);
    }

    /**
     * @return URL: https://www.javaguides.net/2023/04/spring-security-basic-authentication.html
     */
    @GetMapping({"/test", "/test/"})
    public MappingJackson2JsonView info(Authentication authentication) throws EntityNotFoundException {
        MappingJackson2JsonView view = new MappingJackson2JsonView();
        SortedMap<String, String> props = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);

        String userName = authentication.getName();
        props.put("test", "API SERVCAT com Spring Security");
        props.put("user_active", userName);
        view.setAttributesMap(props);
        view.setPrettyPrint(true);
        return view;
    }

}
