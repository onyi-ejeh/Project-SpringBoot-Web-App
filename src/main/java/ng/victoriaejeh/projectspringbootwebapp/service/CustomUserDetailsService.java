package ng.victoriaejeh.projectspringbootwebapp.service;

import ng.victoriaejeh.projectspringbootwebapp.model.AppUser;
import ng.victoriaejeh.projectspringbootwebapp.repository.AppUserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collections;

/**
 * Service that implements {@link UserDetailsService} to load user-specific data.
 * <p>
 * This service retrieves an {@link AppUser} from the {@link AppUserRepository} and converts it into
 * a Spring Security {@link UserDetails} object, which includes the user's username, password, and roles.
 * </p>
 *
 * @author Onyi Ejeh
 * @version 1.0
 * @since 2025
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final AppUserRepository appUserRepository;

    /**
     * Constructs a {@code CustomUserDetailsService} with the specified {@link AppUserRepository}.
     *
     * @param appUserRepository the repository used to retrieve {@link AppUser} entities
     */
    public CustomUserDetailsService(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }

    /**
     * Loads the user by the given username and converts it to a {@link UserDetails} object.
     * <p>
     * The method fetches the user from the {@link AppUserRepository}. If the user is not found,
     * a {@link UsernameNotFoundException} is thrown. Otherwise, the user's details (username, password,
     * and role) are encapsulated in a {@link UserDetails} object. The role is prefixed with "ROLE_" to conform
     * to Spring Security's standards.
     * </p>
     *
     * @param username the username identifying the user whose data is required
     * @return a fully populated {@link UserDetails} object (never {@code null})
     * @throws UsernameNotFoundException if the user could not be found or the user has no GrantedAuthority
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        // Fetching the user from the repository
        AppUser appUser = appUserRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));

        // Returning UserDetails with username, password, and roles.
        return new User(
                appUser.getUsername(),
                appUser.getPassword(),
                Collections.singleton(new SimpleGrantedAuthority("ROLE_" + appUser.getRole())) // Role prepended with "ROLE_"
        );
    }
}
