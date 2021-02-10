package com.gssp.worklogdisplay.controller

import com.gssp.worklogdisplay.model.Employees
import com.gssp.worklogdisplay.model.Worklog
import com.gssp.worklogdisplay.service.DisplayService
import com.sun.corba.se.spi.orbutil.threadpool.Work
import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.cloud.context.config.annotation.RefreshScope
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@Slf4j
@RestController
@RefreshScope
class DisplayController {

    @Autowired
    DisplayService displayService

    @Value('${key.display.dummy}')
    private String check

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/viewEmployees")
    List<Employees> getEmployeeDetails() {
        log.info("START :: DisplayController :: getEmployeeDetails")

        if(log.isDebugEnabled())
            log.debug("checking value :: {}", check)

        List<Employees> employeesList = displayService.getEmployeeDetails()
        log.info("END :: DisplayController :: getEmployeeDetails")
        return employeesList
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/viewDetails/{employee_name}")
    List<Worklog> getByName(@PathVariable("employee_name") String employeeName){
        log.info("START :: DisplayController :: getByName :: employeeName :: {}", employeeName)
        List<Worklog> worklogList = displayService.viewLogByName(employeeName)
        log.info("END :: DisplayController :: getByName :: employeeName :: {}", employeeName)
        return worklogList
    }

}
