package ng.victoriaejeh.projectspringbootwebapp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

/**
 * Represents an application user with authentication and role-based access control.
 */
@Entity
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  // Unique identifier for each user
    private String username;  // Username for the user
    private String password;  // Encrypted password for the user
    private String role;  // Role assigned to the user (e.g., ADMIN, USER)

    /**
     * Constructs an AppUser with a specified username, password, and role.
     *
     * @param username The username of the user.
     * @param password The encrypted password of the user.
     * @param role The role assigned to the user (e.g., ADMIN, USER).
     */
    public AppUser(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    /**
     * Default constructor for JPA.
     */
    public AppUser() {
        // Default constructor required by JPA
    }

    /**
     * Retrieves the unique identifier of the user.
     *
     * @return The user ID.
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the unique identifier of the user.
     *
     * @param id The new user ID.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Retrieves the username of the user.
     *
     * @return The username.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the username of the user.
     *
     * @param username The new username.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Retrieves the encrypted password of the user.
     *
     * @return The encrypted password.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the encrypted password of the user.
     *
     * @param password The new encrypted password.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Retrieves the role assigned to the user.
     *
     * @return The user role (e.g., ADMIN, USER).
     */
    public String getRole() {
        return role;
    }

    /**
     * Sets the role assigned to the user.
     *
     * @param role The new user role (e.g., ADMIN, USER).
     */
    public void setRole(String role) {
        this.role = role;
    }
}
