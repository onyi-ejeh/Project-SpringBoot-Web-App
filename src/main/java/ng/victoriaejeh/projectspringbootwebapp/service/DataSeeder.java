package ng.victoriaejeh.projectspringbootwebapp.service;

import jakarta.annotation.PostConstruct;
import ng.victoriaejeh.projectspringbootwebapp.model.AppUser;
import ng.victoriaejeh.projectspringbootwebapp.repository.AppUserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * Component responsible for seeding initial data into the database.
 * <p>
 * This component checks if the user repository is empty and, if so, populates it with default users.
 * Each default user is created with an encoded password and an assigned role.
 * </p>
 *
 * <p>
 * The seeding operation is executed after the bean initialization, as indicated by the {@link PostConstruct} annotation.
 * </p>
 *
 * @author Onyi Ejeh
 * @version 1.0
 * @since 2025
 */
@Component
public class DataSeeder {

    private final AppUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    /**
     * Constructs a {@code DataSeeder} with the specified dependencies.
     *
     * @param userRepository  the repository used to persist and retrieve {@link AppUser} entities
     * @param passwordEncoder the password encoder used to securely encode user passwords
     */
    public DataSeeder(AppUserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Initializes the database with default users if no users exist.
     * <p>
     * This method is invoked after the bean's properties have been set. It checks whether the user repository
     * is empty. If it is, three default users ("admin", "manager", and "Vicky") are created with their passwords
     * encoded and assigned respective roles ("ADMIN", "MANAGER", and "USER").
     * </p>
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
