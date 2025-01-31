package ng.victoriaejeh.projectspringbootwebapp.resource;

import ng.victoriaejeh.projectspringbootwebapp.model.ManagerInfo;
import ng.victoriaejeh.projectspringbootwebapp.service.ManagerComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * Controller responsible for handling manager-related HTTP requests.
 * <p>
 * This controller provides an endpoint for displaying manager information.
 * It leverages the {@link ManagerComponent} service to retrieve a list of manager data,
 * which is then added to the model for rendering in the view.
 * </p>
 *
 * <p>
 * Annotated with {@link Controller} to designate it as a Spring MVC controller.
 * </p>
 *
 * @author Onyi Ejeh
 * @version 1.0
 * @since 2025
 */
@Controller
public class ManagerController {

    private final ManagerComponent managerComponentService;

    /**
     * Constructs a {@code ManagerController} with the specified {@link ManagerComponent} service.
     * <p>
     * This constructor uses constructor-based dependency injection to inject the required service.
     * </p>
     *
     * @param managerComponentService the service responsible for handling manager information
     */
    @Autowired
    public ManagerController(ManagerComponent managerComponentService) {
        this.managerComponentService = managerComponentService;
    }

    /**
     * Handles GET requests to the "/manager" endpoint.
     * <p>
     * Retrieves a list of manager information from the {@link ManagerComponent} service,
     * adds the list to the provided model under the attribute "managerInfo",
     * and returns the view name for the manager page.
     * </p>
     *
     * @param model the {@link Model} object used to pass attributes to the view
     * @return the name of the manager view template (i.e., "manager")
     */
    @GetMapping("/manager")
    public String managerPage(Model model) {
        List<ManagerInfo> managerInfoList = managerComponentService.getManagerInfo();
        model.addAttribute("managerInfo", managerInfoList);
        return "manager";
    }
}
