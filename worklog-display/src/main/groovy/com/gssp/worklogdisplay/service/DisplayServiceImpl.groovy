package com.gssp.worklogdisplay.service

import com.gssp.worklogdisplay.feign.UpdateClient
import com.gssp.worklogdisplay.model.Employees
import com.gssp.worklogdisplay.model.Worklog
import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Slf4j
@Service
class DisplayServiceImpl implements DisplayService {

    @Autowired
    UpdateClient updateClient

    @Override
    List<Employees> getEmployeeDetails() {
        log.info("START :: DisplayServiceImpl :: getEmployeeDetails")
        List<Employees> employeesList = new ArrayList<>()
        employeesList.add(new Employees('deepak', 'Deepak', 'Programmer Analyst Trainee'))
        employeesList.add(new Employees('gowri', 'Gowri', 'Programmer Analyst Trainee'))
        employeesList.add(new Employees('janet', 'Janet', 'Programmer Analyst Trainee'))
        employeesList.add(new Employees('thripura', 'Thripura', 'Programmer Analyst Trainee'))
        employeesList.add(new Employees('rohith', 'Rohith', 'Programmer Analyst Trainee'))
        employeesList.add(new Employees('gowshik', 'Gowshik', 'Programmer Analyst Trainee'))
        employeesList.add(new Employees('siva', 'Siva', 'Programmer Analyst Trainee'))
        employeesList.add(new Employees('barath', 'Barath', 'Programmer Analyst Trainee'))
        employeesList.add(new Employees('murali', 'Murali', 'Programmer Analyst Trainee'))
        employeesList.add(new Employees('nagarjun', 'Nagarjun', 'Programmer Analyst Trainee'))
        log.info("END :: DisplayServiceImpl :: getEmployeeDetails")
        return employeesList
    }

    @Override
    List<Worklog> viewLogByName(String employeeName) {
        log.info("START :: DisplayServiceImpl :: viewLogByName :: employeeName :: {}", employeeName)
        List<Worklog> worklogList = updateClient.viewLogByName(employeeName)
        log.info("END :: DisplayServiceImpl :: viewLogByName :: employeeName :: {}", employeeName)
        return worklogList
    }

}
