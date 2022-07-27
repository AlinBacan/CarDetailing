package ro.sda.javaro35.finalProject.configuration;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import ro.sda.javaro35.finalProject.services.UserService;

@Configuration
@AllArgsConstructor
public class SecurtityConfigUpdate {



    UserService userService;

    PasswordEncoder passwordEncoder;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeRequests((authz) -> authz.antMatchers(HttpMethod.GET, "/api/cars").hasAnyRole("ADMIN", "CARS")
//                .antMatchers(HttpMethod.POST, "/api/author").authenticated()
//                .antMatchers("/api/users/**").hasAuthority("ROLE_USER_ADMIN")
                .anyRequest().permitAll() )
                .formLogin().and()
                .httpBasic().and()
                .logout()
                .and()
                .csrf().ignoringAntMatchers("/api/**")
                .and()
                .headers().frameOptions().disable()
        ;
        return http.build();
    }


    @Bean
    public DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userService);
        authenticationProvider.setPasswordEncoder(passwordEncoder);
        return authenticationProvider;
    }
}
