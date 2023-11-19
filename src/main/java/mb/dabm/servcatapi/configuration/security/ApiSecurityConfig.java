package mb.dabm.servcatapi.configuration.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class ApiSecurityConfig {

    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
            .csrf(crsf -> crsf.disable())
            .cors(AbstractHttpConfigurer::disable)
            .authorizeHttpRequests((authorize) -> {
                authorize
                    //.requestMatchers(HttpMethod.POST,"/auth/register").hasRole("ADMIN")
                    // .requestMatchers(HttpMethod.POST,"/auth/login").permitAll()
                    //.requestMatchers("/funcionarios").hasRole("ADMIN")
                    .requestMatchers("/info/**").permitAll()
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
            .username("servcat")
            .password(passwordEncoder().encode("s3rvc@t"))
            .roles("USER")
            .build();

        // responsável para cadastrar registros do NMCRL.
        UserDetails admin = User.builder()
            .username("admin")
            .password(passwordEncoder().encode("nmcrl@dabm"))
            .roles("admin")
            .build();

        return new InMemoryUserDetailsManager(servcat, admin);
    }

}
