package ng.victoriaejeh.projectspringbootwebapp.resource;

import ng.victoriaejeh.projectspringbootwebapp.model.ManagerInfo;
import ng.victoriaejeh.projectspringbootwebapp.service.ManagerComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * Controller for managing manager-related operations.
 */
@Controller
public class ManagerController {

    private final ManagerComponent managerComponentService;

    /**
     * Constructor-based dependency injection.
     * @param managerComponentService Service handling manager information.
     */
    @Autowired
    public ManagerController(ManagerComponent managerComponentService) {
        this.managerComponentService = managerComponentService;
    }

    /**
     * Handles GET requests to "/manager" and displays manager information.
     * @param model Model to pass manager data to the view.
     * @return The "manager" view template.
     */
    @GetMapping("/manager")
    public String managerPage(Model model) {
        List<ManagerInfo> managerInfoList = managerComponentService.getManagerInfo();
        model.addAttribute("managerInfo", managerInfoList);
        return "manager";
    }
}
