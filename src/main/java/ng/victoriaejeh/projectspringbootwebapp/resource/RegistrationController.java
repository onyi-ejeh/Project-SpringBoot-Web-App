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

@Controller
public class RegistrationController {

    private final AppUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public RegistrationController(AppUserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        // Add an empty DTO object to bind form data
        model.addAttribute("newuser", new NewUserRegDTO());
        return "register"; // Return the view for the registration form
    }

    @PostMapping("/register")
    public String processRegistrationForm(@ModelAttribute("newuser") NewUserRegDTO newUserRegDTO, Model model) {
        try {
            // Check if username already exists
            if (userRepository.findByUsername(newUserRegDTO.getUsername()).isPresent()) {
                throw new RuntimeException("Username already exists. Please choose a different one.");
            }

            // Create a new user and set details
            AppUser newUser = new AppUser();
            newUser.setUsername(newUserRegDTO.getUsername());
            newUser.setPassword(passwordEncoder.encode(newUserRegDTO.getPassword()));
            newUser.setRole(newUserRegDTO.getRole()); // Ensure the role is set

            // Save the new user to the repository
            userRepository.save(newUser);

            // Add a success message and redirect to the login page
            model.addAttribute("successMessage", "Registration successful!");
            return "redirect:/login";

        } catch (RuntimeException e) {
            // Handle registration error and reload the form with the error message
            model.addAttribute("errorMessage", e.getMessage());
            return "register";
        }
    }
}
