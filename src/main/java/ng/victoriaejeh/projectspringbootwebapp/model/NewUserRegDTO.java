package ng.victoriaejeh.projectspringbootwebapp.model;

/**
 * Data Transfer Object (DTO) for new user registration.
 * Used to transfer user data between layers of the application.
 */
public class NewUserRegDTO {

    private String username;  // Username of the new user
    private String password;  // Password of the new user
    private String role;      // Role assigned to the new user (e.g., USER, ADMIN)

    /**
     * Retrieves the username of the new user.
     *
     * @return The username of the new user.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the username of the new user.
     *
     * @param username The new username.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Retrieves the password of the new user.
     *
     * @return The password of the new user.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password of the new user.
     *
     * @param password The new password.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Retrieves the role assigned to the new user.
     *
     * @return The role of the new user.
     */
    public String getRole() {
        return role;
    }

    /**
     * Sets the role assigned to the new user.
     *
     * @param role The new role (e.g., USER, ADMIN).
     */
    public void setRole(String role) {
        this.role = role;
    }
}
