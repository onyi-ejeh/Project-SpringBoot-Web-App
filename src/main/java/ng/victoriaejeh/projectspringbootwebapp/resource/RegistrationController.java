package ng.victoriaejeh.projectspringbootwebapp.resource;

import ng.victoriaejeh.projectspringbootwebapp.model.AppUser;
import ng.victoriaejeh.projectspringbootwebapp.model.NewUserRegDTO;
import ng.victoriaejeh.projectspringbootwebapp.repository.AppUserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * Controller for handling user registration operations.
 * <p>
 * This controller provides endpoints to display the registration form and process the
 * registration form submission. It uses an {@link AppUserRepository} to interact with the
 * persistence layer and a {@link PasswordEncoder} to securely encode user passwords.
 * </p>
 *
 * @author Onyi Ejeh
 * @version 1.0
 * @since 2025
 */
@Controller
public class RegistrationController {

    private final AppUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    /**
     * Constructs a {@code RegistrationController} with the specified dependencies.
     *
     * @param userRepository  the repository used to persist and retrieve {@link AppUser} entities
     * @param passwordEncoder the password encoder used to securely encode user passwords
     */
    public RegistrationController(AppUserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Displays the user registration form.
     * <p>
     * This method adds a new {@link NewUserRegDTO} to the model to bind form data,
     * and returns the view name for the registration page.
     * </p>
     *
     * @param model the {@link Model} used to pass attributes to the view
     * @return the name of the registration view template (i.e., "register")
     */
    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("newuser", new NewUserRegDTO());
        return "register";
    }

    /**
     * Processes the registration form submission.
     * <p>
     * This method validates the new user's details, ensuring that the username is unique.
     * On successful registration, it encodes the password, creates a new {@link AppUser},
     * saves it to the repository, and redirects the user to the login page with a success message.
     * If an error occurs, the registration form is reloaded with an error message.
     * </p>
     *
     * @param newUserRegDTO a {@link NewUserRegDTO} containing the user's registration details
     * @param model         the {@link Model} used to pass messages to the view
     * @return a redirect to the login page upon success, or the registration view if an error occurs
     */
    @PostMapping("/register")
    public String processRegistrationForm(@ModelAttribute("newuser") NewUserRegDTO newUserRegDTO, Model model) {
        try {
            // Check if the username already exists
            if (userRepository.findByUsername(newUserRegDTO.getUsername()).isPresent()) {
                throw new RuntimeException("Username already exists. Please choose a different one.");
            }

            // Create and save the new user
            AppUser newUser = new AppUser();
            newUser.setUsername(newUserRegDTO.getUsername());
            newUser.setPassword(passwordEncoder.encode(newUserRegDTO.getPassword()));
            newUser.setRole(newUserRegDTO.getRole());

            userRepository.save(newUser);

            // Redirect to login page with a success message
            model.addAttribute("successMessage", "Registration successful!");
            return "redirect:/login";

        } catch (RuntimeException e) {
            // Handle registration error and reload the form with an error message
            model.addAttribute("errorMessage", e.getMessage());
            return "register";
        }
    }
}
