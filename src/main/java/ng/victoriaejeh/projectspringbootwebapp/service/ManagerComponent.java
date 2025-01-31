package ng.victoriaejeh.projectspringbootwebapp.service;

import ng.victoriaejeh.projectspringbootwebapp.model.ManagerInfo;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Component responsible for handling operations related to manager reports.
 * <p>
 * This component provides functionality to retrieve a list of predefined manager reports.
 * Each report is represented by a {@link ManagerInfo} object containing a title and a description.
 * </p>
 *
 * @author Onyi Ejeh
 * @version 1.0
 * @since 2025
 */
@Component
public class ManagerComponent {

    /**
     * Returns a list of {@link ManagerInfo} objects, each representing a manager's report with a title and description.
     * <p>
     * This method creates and returns a list of sample manager reports for demonstration purposes.
     * </p>
     *
     * @return a {@link List} of {@link ManagerInfo} instances containing predefined manager reports
     */
    public List<ManagerInfo> getManagerInfo() {
        List<ManagerInfo> managerInfoList = new ArrayList<>();

        // Adding predefined manager reports to the list
        managerInfoList.add(new ManagerInfo("Manager Report 1", "Details about Manager Report 1"));
        managerInfoList.add(new ManagerInfo("Manager Report 2", "Details about Manager Report 2"));
        managerInfoList.add(new ManagerInfo("Manager Report 3", "Details about Manager Report 3"));

        return managerInfoList;
    }
}
