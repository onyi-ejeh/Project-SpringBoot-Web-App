package ng.victoriaejeh.projectspringbootwebapp.resource;

import ng.victoriaejeh.projectspringbootwebapp.model.AppUser;
import ng.victoriaejeh.projectspringbootwebapp.repository.AppUserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Controller for handling user-related requests and page navigation.
 */
@Controller
public class UserController {

    private final AppUserRepository appUserRepository;

    /**
     * Constructor injection for the AppUserRepository.
     */
    public UserController(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }

    /**
     * Handles requests to the home page.
     * Adds a welcome message to the model.
     */
    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("welcome", "You are logged in");
        return "index"; // Maps to index.html
    }

    /**
     * Handles requests to the user page.
     * Retrieves the currently authenticated username and passes it to the view.
     */
    @GetMapping("/user")
    public String userPage(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();

        model.addAttribute("username", username);
        return "user"; // Maps to user.html
    }

    /**
     * Handles requests to the error page.
     */
    @GetMapping("/error")
    public String errorPage(Model model) {
        return "error"; // Maps to error.html
    }

    /**
     * Handles requests to the user profile page.
     * Retrieves the authenticated user's details from the database.
     */
    @GetMapping("/profile")
    public String userProfile(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();

        // Retrieve user from repository
        AppUser user = appUserRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        model.addAttribute("user", user); // Pass user details to the view
        return "profile"; // Maps to profile.html
    }
}
