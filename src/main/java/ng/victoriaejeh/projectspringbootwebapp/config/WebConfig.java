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
     *
     * @param customUserDetailsService The custom user details service used for authentication.
     */
    public WebConfig(CustomUserDetailsService customUserDetailsService) {
        this.customUserDetailsService = customUserDetailsService;
    }

    /**
     * Configures the security filter chain to handle authentication and authorization.
     *
     * @param http The HttpSecurity object used to configure security settings.
     * @return The configured SecurityFilterChain.
     * @throws Exception If an error occurs while configuring security settings.
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
     *
     * @param userDetailsService The service used to load user details.
     * @param passwordEncoder The encoder used to verify passwords.
     * @return The configured AuthenticationManager.
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
     * Provides a bean for loading user details from a custom service.
     *
     * @return An instance of UserDetailsService.
     */
    @Bean
    public UserDetailsService userDetailsService() {
        return customUserDetailsService;
    }

    /**
     * Provides a bean for password encoding using BCrypt.
     *
     * @return An instance of PasswordEncoder using BCrypt hashing.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
