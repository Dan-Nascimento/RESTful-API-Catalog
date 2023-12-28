package mb.dabm.servcatapi.configuration.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

import static org.springframework.security.config.http.MatcherType.mvc;
import static org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher;

@Configuration
@EnableWebSecurity
public class ApiSecurityConfig {

    @Value("${app.user}")
    String appUser;
    @Value("${app.password}")
    String appPassword;

    @Value("${app.admin.user}")
    String appAdminUser;

    @Value("${app.admin.password}")
    String appAdminPassword;
//
//    MvcRequestMatcher.Builder mvcMatcherBuilder;
//
//    public ApiSecurityConfig(HandlerMappingIntrospector introspector){
//        this.mvcMatcherBuilder = new MvcRequestMatcher.Builder(introspector);
//    }

    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http, HandlerMappingIntrospector introspector) throws Exception {

        MvcRequestMatcher.Builder mvcMatcherBuilder = new MvcRequestMatcher.Builder(introspector).servletPath("/path");

        http
            .csrf(crsf -> crsf.disable())
            .cors(AbstractHttpConfigurer::disable)
            .authorizeHttpRequests((authorize) -> {
                authorize
                    .requestMatchers(mvcMatcherBuilder.pattern("/admin")).hasRole("ADMIN")
                    .requestMatchers(antMatcher("/api/**")).permitAll()
                    .anyRequest().authenticated();
            })
            .httpBasic(Customizer.withDefaults())
        ;

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {

        // responsável para visualizar registros do NMCRL.
        UserDetails servcat = User.builder()
//            .username("servcat")
            .username(appUser)
//            .password(passwordEncoder().encode("s3rvc@t"))
            .password(passwordEncoder().encode(appPassword))
            .roles("USER")
            .build();

        // responsável para cadastrar registros do NMCRL.
        UserDetails admin = User.builder()
//            .username("admin")
            .username(appAdminUser)
//            .password(passwordEncoder().encode("nmcrl@dabm"))
            .password(passwordEncoder().encode(appAdminPassword))
            .roles("admin")
            .build();

        return new InMemoryUserDetailsManager(servcat, admin);
    }

}
