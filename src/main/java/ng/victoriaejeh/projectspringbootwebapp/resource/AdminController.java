package ng.victoriaejeh.projectspringbootwebapp.resource;

import ng.victoriaejeh.projectspringbootwebapp.model.AdminDocument;
import ng.victoriaejeh.projectspringbootwebapp.service.AdminComponent;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class AdminController {

    private final AdminComponent adminDocumentService;

    public AdminController(AdminComponent adminDocumentService) {
        this.adminDocumentService = adminDocumentService;
    }

    @GetMapping("/admin")
    public String showAdminpage(Model model) {

        // Add sample admin documents
        List<AdminDocument> adminDocuments = adminDocumentService.getAdminDocuments();
        model.addAttribute("documents", adminDocuments);
        // Maps to admin.html
        return "admin";

    }



}
