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

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final AppUserRepository appUserRepository;

    /**
     *   Constructor to initialize AppUserRepository
     */

    public CustomUserDetailsService(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }

    /**
     *  Loads a user by username and converts it to a UserDetails object with roles
      */

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        /**
         *  Fetching the user from the repository
         */

        AppUser appUser = appUserRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));

        /**
         *  Returning UserDetails with username, password, and roles
          */

        return new User(
                appUser.getUsername(),
                appUser.getPassword(),
                Collections.singleton(new SimpleGrantedAuthority("ROLE_" + appUser.getRole())) // Role prepended with "ROLE_"
        );
    }
}
