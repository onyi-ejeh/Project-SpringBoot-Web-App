package ng.victoriaejeh.projectspringbootwebapp.service;

import jakarta.annotation.PostConstruct;
import ng.victoriaejeh.projectspringbootwebapp.model.AppUser;
import ng.victoriaejeh.projectspringbootwebapp.repository.AppUserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataSeeder {

    private final AppUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    /**
     * Constructor to initialize dependencies
     */

    public DataSeeder(AppUserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Populates the database with initial users if no users exist
     */

    @PostConstruct
    public void init() {
        if (userRepository.count() == 0) { // Checks if the user table is empty
            // Creating and saving default users with encoded passwords and roles
            userRepository.save(new AppUser("admin", passwordEncoder.encode("admin"), "ADMIN"));
            userRepository.save(new AppUser("manager", passwordEncoder.encode("manager"), "MANAGER"));
            userRepository.save(new AppUser("Vicky", passwordEncoder.encode("user"), "USER"));
        }
    }
}
