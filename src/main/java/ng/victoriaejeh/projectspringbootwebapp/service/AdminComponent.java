package ng.victoriaejeh.projectspringbootwebapp.service;

import ng.victoriaejeh.projectspringbootwebapp.model.AdminDocument;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AdminComponent {
        public List<AdminDocument> getAdminDocuments() {
            List<AdminDocument> adminDocuments = new ArrayList<>();
            adminDocuments.add(new AdminDocument("Report 1", "Detailed description of Report 1"));
            adminDocuments.add(new AdminDocument("Report 2", "Detailed description of Report 2"));
            adminDocuments.add(new AdminDocument("Report 3", "Detailed description of Report 3"));
            return adminDocuments;
        }


}
