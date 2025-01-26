package ng.victoriaejeh.projectspringbootwebapp.service;


import ng.victoriaejeh.projectspringbootwebapp.model.ManagerInfo;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ManagerComponent {

    public List<ManagerInfo> getManagerInfo() {
        List<ManagerInfo> managerInfoList = new ArrayList<>();
        managerInfoList.add(new ManagerInfo("Manager Report 1", "Details about Manager Report 1"));
        managerInfoList.add(new ManagerInfo("Manager Report 2", "Details about Manager Report 2"));
        managerInfoList.add(new ManagerInfo("Manager Report 3", "Details about Manager Report 3"));
        return managerInfoList;
    }



}
