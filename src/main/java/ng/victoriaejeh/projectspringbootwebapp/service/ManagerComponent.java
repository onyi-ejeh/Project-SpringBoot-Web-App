package ng.victoriaejeh.projectspringbootwebapp.service;

import ng.victoriaejeh.projectspringbootwebapp.model.ManagerInfo;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ManagerComponent {

    /**
     * Returns a list of ManagerInfo objects, each representing a manager's report with title and description
      */

    public List<ManagerInfo> getManagerInfo() {
        List<ManagerInfo> managerInfoList = new ArrayList<>();

        /**
         *    Adding predefined manager reports to the list
         */
        managerInfoList.add(new ManagerInfo("Manager Report 1", "Details about Manager Report 1"));
        managerInfoList.add(new ManagerInfo("Manager Report 2", "Details about Manager Report 2"));
        managerInfoList.add(new ManagerInfo("Manager Report 3", "Details about Manager Report 3"));
        return managerInfoList;
    }
}
