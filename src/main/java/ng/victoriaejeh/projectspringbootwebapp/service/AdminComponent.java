package ng.victoriaejeh.projectspringbootwebapp.service;

import ng.victoriaejeh.projectspringbootwebapp.model.AdminDocument;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Component responsible for handling operations related to admin documents.
 * <p>
 * This component provides functionality to retrieve a list of sample admin documents,
 * each containing a title and a detailed description.
 * </p>
 *
 * @author Onyi Ejeh
 * @version 1.0
 * @since 2025
 */
@Component
public class AdminComponent {

    /**
     * Retrieves a list of {@link AdminDocument} objects with predefined titles and descriptions.
     * <p>
     * This method creates a list of sample admin documents for demonstration purposes.
     * Each document contains a title and a detailed description.
     * </p>
     *
     * @return a {@link List} of {@link AdminDocument} instances
     */
    public List<AdminDocument> getAdminDocuments() {
        List<AdminDocument> adminDocuments = new ArrayList<>();

        // Adding sample reports with titles and descriptions
        adminDocuments.add(new AdminDocument("Report 1", "Detailed description of Report 1"));
        adminDocuments.add(new AdminDocument("Report 2", "Detailed description of Report 2"));
        adminDocuments.add(new AdminDocument("Report 3", "Detailed description of Report 3"));

        return adminDocuments;
    }
}
