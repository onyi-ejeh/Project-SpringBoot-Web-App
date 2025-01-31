package ng.victoriaejeh.projectspringbootwebapp.resource;

import ng.victoriaejeh.projectspringbootwebapp.model.AdminDocument;
import ng.victoriaejeh.projectspringbootwebapp.service.AdminComponent;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * Controller for handling admin-related requests.
 */
@Controller
public class AdminController {

    private final AdminComponent adminDocumentService;

    /**
     * Constructor injection for the AdminComponent service.
     */
    public AdminController(AdminComponent adminDocumentService) {
        this.adminDocumentService = adminDocumentService;
    }

    /**
     * Handles requests to the admin page.
     * Retrieves a list of admin documents and adds them to the model.
     */
    @GetMapping("/admin")
    public String showAdminPage(Model model) {
        List<AdminDocument> adminDocuments = adminDocumentService.getAdminDocuments();
        model.addAttribute("documents", adminDocuments);

        return "admin"; // Maps to admin.html
    }
}
