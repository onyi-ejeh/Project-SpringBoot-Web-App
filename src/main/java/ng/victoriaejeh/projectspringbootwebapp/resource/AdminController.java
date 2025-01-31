package ng.victoriaejeh.projectspringbootwebapp.resource;

import ng.victoriaejeh.projectspringbootwebapp.model.AdminDocument;
import ng.victoriaejeh.projectspringbootwebapp.service.AdminComponent;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * Controller responsible for handling admin-related HTTP requests.
 * Provides an endpoint for rendering the admin page with a list of admin documents.
 *
 * <p>This controller interacts with the {@link AdminComponent} service
 * to retrieve data and passes it to the view.</p>
 *
 * <p>Annotated with {@link Controller} to indicate that it's a Spring MVC controller.</p>
 *
 * @author Onyi Ejeh
 * @version 1.0
 * @since 2025
 */
@Controller
public class AdminController {

    private final AdminComponent adminDocumentService;

    /**
     * Constructs an {@code AdminController} with the specified {@code AdminComponent} service.
     *
     * @param adminDocumentService the service responsible for handling admin document operations
     */
    public AdminController(AdminComponent adminDocumentService) {
        this.adminDocumentService = adminDocumentService;
    }

    /**
     * Handles GET requests to the "/admin" endpoint.
     * Retrieves a list of admin documents from the service and adds them to the model.
     *
     * @param model the {@link Model} object used to pass attributes to the view
     * @return the name of the admin view template (i.e., "admin.html")
     */
    @GetMapping("/admin")
    public String showAdminPage(Model model) {
        List<AdminDocument> adminDocuments = adminDocumentService.getAdminDocuments();
        model.addAttribute("documents", adminDocuments);

        return "admin"; // Maps to admin.html
    }
}
