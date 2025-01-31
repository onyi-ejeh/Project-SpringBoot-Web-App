package ng.victoriaejeh.projectspringbootwebapp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  // Unique identifier for each user
    private String username;  // Username for the user
    private String password;  // Encrypted password for the user
    private String role;  // Role assigned to the user (e.g., ADMIN, USER)

    /**
     * Constructor to initialize AppUser with username, password, and role
      */

    public AppUser(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    /**
     *  Default constructor for JPA
     */

    public AppUser() {

    }

    /**
     *     Getter for id
     */

    public Long getId() {
        return id;
    }

    /**
     * Getter for username
      */

    public String getUsername() {
        return username;
    }

    /**
     *  Getter for password
     */

    public String getPassword() {
        return password;
    }

    /**
     * Getter for role
     */

    public String getRole() {
        return role;
    }

    /**
     *    Setter for id
     */

    public void setId(Long id) {
        this.id = id;
    }

    /**
     *  Setter for username
     */

    public void setUsername(String username) {
        this.username = username;
    }

    /**
     *  Setter for password
      */

    public void setPassword(String password) {
        this.password = password;
    }

    /**
     *  Setter for role
     */

    public void setRole(String role) {
        this.role = role;
    }
}
