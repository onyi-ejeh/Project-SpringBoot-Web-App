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

/**
 * Security configuration class for handling authentication and authorization.
 */
@Configuration
@EnableWebSecurity
public class WebConfig {

    private final CustomUserDetailsService customUserDetailsService;

    /**
     * Constructor injection for CustomUserDetailsService.
     */
    public WebConfig(CustomUserDetailsService customUserDetailsService) {
        this.customUserDetailsService = customUserDetailsService;
    }

    /**
     * Configures security filter chain for handling authorization.
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/register", "/login", "/error").permitAll() // Public endpoints
                        .requestMatchers("/manager").hasRole("MANAGER") // Restricted to MANAGER role
                        .requestMatchers("/admin").hasAnyRole("ADMIN", "MANAGER") // Admin & Manager access
                        .requestMatchers("/user").hasAnyRole("USER", "ADMIN", "MANAGER") // User, Admin & Manager
                        .requestMatchers("/").hasAnyRole("USER", "ADMIN", "MANAGER") // Homepage access
                        .anyRequest().authenticated() // All other requests require authentication
                )
                .formLogin(Customizer.withDefaults()) // Default login handling
                .exceptionHandling(Customizer.withDefaults()) // Default exception handling
                .logout(Customizer.withDefaults()); // Default logout handling

        return http.build();
    }

    /**
     * Custom AuthenticationManager for validating user credentials.
     */
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
                    userDetails,  // Authenticated user object
                    password,
                    userDetails.getAuthorities()
            );
        };
    }

    /**
     * Bean for loading user details from a custom service.
     */
    @Bean
    public UserDetailsService userDetailsService() {
        return customUserDetailsService;
    }

    /**
     * Bean for password encoding using BCrypt.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
