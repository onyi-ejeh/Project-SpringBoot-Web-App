package ng.victoriaejeh.projectspringbootwebapp.config;

import ng.victoriaejeh.projectspringbootwebapp.service.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebConfig {

    private final CustomUserDetailsService customUserDetailsService;

    public WebConfig(CustomUserDetailsService customUserDetailsService) {
        this.customUserDetailsService = customUserDetailsService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/register", "/login", "/error").permitAll()
                        .requestMatchers("/manager").hasRole("MANAGER")
                        .requestMatchers("/admin").hasAnyRole("ADMIN", "MANAGER")
                        .requestMatchers("/user").hasAnyRole("USER", "ADMIN", "MANAGER")
                        .requestMatchers("/").hasAnyRole("USER", "ADMIN", "MANAGER")
                        .anyRequest().authenticated()
                )

                .formLogin(Customizer.withDefaults())
                .exceptionHandling(Customizer.withDefaults())
                .logout(Customizer.withDefaults());
//                .formLogin(form -> form
//                        .loginPage("/login")
//                        .permitAll()
//                ); // ✅ Fixed: Replaced colon with semicolon
//        http.csrf(AbstractHttpConfigurer::disable);
//
//        http.logout(logout -> logout
//                .logoutUrl("/logout")
//                .logoutSuccessUrl("/")
//                .invalidateHttpSession(true)
//                .clearAuthentication(true)
//                .permitAll()
//        );

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(
            UserDetailsService userDetailsService,
            PasswordEncoder passwordEncoder) {
        return authentication -> {
            String username = authentication.getPrincipal().toString();
            String password = authentication.getCredentials().toString();

            UserDetails userDetails = userDetailsService.loadUserByUsername(username);

            if (userDetails == null) {
                throw new BadCredentialsException("User not found");
            }

            if (!passwordEncoder.matches(password, userDetails.getPassword())) {
                throw new BadCredentialsException("Wrong login credentials");
            }

            return new UsernamePasswordAuthenticationToken(
                    userDetails,  // ✅ Corrected: Pass userDetails instead of username
                    password,
                    userDetails.getAuthorities()
            );
        };
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return customUserDetailsService;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
