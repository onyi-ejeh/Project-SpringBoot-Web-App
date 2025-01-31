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
 * Controller for handling user registration.
 */
@Controller
public class RegistrationController {

    private final AppUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    /**
     * Constructor for injecting dependencies.
     */
    public RegistrationController(AppUserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Displays the registration form.
     * @param model Model to bind form data.
     * @return The registration page view.
     */
    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("newuser", new NewUserRegDTO());
        return "register";
    }

    /**
     * Processes the registration form submission.
     * @param newUserRegDTO DTO containing user registration details.
     * @param model Model to pass success or error messages.
     * @return Redirects to login page on success, reloads registration page on error.
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
