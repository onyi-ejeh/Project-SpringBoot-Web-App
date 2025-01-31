package ng.victoriaejeh.projectspringbootwebapp.repository;

import ng.victoriaejeh.projectspringbootwebapp.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

/**
 * Repository interface for managing AppUser entities.
 * Extends JpaRepository to provide standard CRUD operations.
 */
public interface AppUserRepository extends JpaRepository<AppUser, Long> {

    /**
     * Finds a user by their username.
     * @param username The username of the user.
     * @return An Optional containing the user if found, otherwise empty.
     */
    Optional<AppUser> findByUsername(String username);
}
