package ng.victoriaejeh.projectspringbootwebapp.resource;

import ng.victoriaejeh.projectspringbootwebapp.model.AppUser;
import ng.victoriaejeh.projectspringbootwebapp.repository.AppUserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Controller responsible for handling user-related requests and page navigation.
 * <p>
 * This controller provides endpoints for home, user, error, and profile pages. It leverages the
 * {@link AppUserRepository} to retrieve user details from the database and the Spring Security context
 * to access the currently authenticated user.
 * </p>
 *
 * @author Onyi Ejeh
 * @version 1.0
 * @since 2025
 */
@Controller
public class UserController {

    private final AppUserRepository appUserRepository;

    /**
     * Constructs a {@code UserController} with the specified {@link AppUserRepository}.
     *
     * @param appUserRepository the repository for {@link AppUser} persistence and retrieval operations
     */
    public UserController(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }

    /**
     * Handles requests to the home page.
     * <p>
     * Adds a welcome message to the model and returns the view for the home page.
     * </p>
     *
     * @param model the {@link Model} object used to pass attributes to the view
     * @return the name of the home view template (i.e., "index")
     */
    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("welcome", "You are logged in");
        return "index"; // Maps to index.html
    }

    /**
     * Handles requests to the user page.
     * <p>
     * Retrieves the currently authenticated user's username from the Spring Security context and
     * adds it to the model.
     * </p>
     *
     * @param model the {@link Model} used to pass attributes to the view
     * @return the name of the user view template (i.e., "user")
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
     * <p>
     * Simply returns the view for the error page.
     * </p>
     *
     * @param model the {@link Model} used to pass attributes to the view (if necessary)
     * @return the name of the error view template (i.e., "error")
     */
    @GetMapping("/error")
    public String errorPage(Model model) {
        return "error"; // Maps to error.html
    }

    /**
     * Handles requests to the user profile page.
     * <p>
     * Retrieves the authenticated user's details from the database using the {@link AppUserRepository}
     * and adds them to the model.
     * </p>
     *
     * @param model the {@link Model} used to pass attributes to the view
     * @return the name of the profile view template (i.e., "profile")
     * @throws RuntimeException if the authenticated user is not found in the repository
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
