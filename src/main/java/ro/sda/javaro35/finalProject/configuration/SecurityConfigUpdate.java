package ro.sda.javaro35.finalProject.configuration;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import ro.sda.javaro35.finalProject.services.UserService;

@Configuration
@AllArgsConstructor
public class SecurityConfigUpdate {


    UserService userService;

    PasswordEncoder passwordEncoder;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeRequests((authz) -> authz.antMatchers(HttpMethod.GET, "/api/service/**").authenticated()
                        .antMatchers(("/users/**")).hasRole("ADMIN")
                        .anyRequest().permitAll())
                .formLogin().and()
                .httpBasic().and()
                .logout().clearAuthentication(true).invalidateHttpSession(true).deleteCookies("JSESSIONID")
                .and()
                .csrf().ignoringAntMatchers("/api/**")
                .and()
                .headers().frameOptions().disable()
        ;
        return http.build();
    }


    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userService);
        authenticationProvider.setPasswordEncoder(passwordEncoder);
        return authenticationProvider;
    }
}