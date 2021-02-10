package com.gssp.worklogupdate.controller

import com.gssp.worklogupdate.model.Login
import com.gssp.worklogupdate.model.Worklog
import com.gssp.worklogupdate.service.UpdateService
import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.cloud.context.config.annotation.RefreshScope
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@Slf4j
@RestController
@RefreshScope
class UpdateController {

    @Autowired
    UpdateService updateService

    @Value('${key.dummy}')
    private String check

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/login")
    Boolean checkLogin(@RequestBody Login login)  {
        log.info("START :: UpdateController :: checkLogin :: login :: {}", login.toString())
        log.info("checking value :: {}", check)
        Boolean isLoggedIn = updateService.loginDetails(login)
        log.info("END :: UpdateController :: checkLogin :: login :: {}", login.toString())
        return isLoggedIn
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/addDetails")
    String addEmployeeDetails(@RequestBody Worklog worklog)  {
        log.info("START :: UpdateController :: addEmployeeDetails :: worklog :: {}", worklog.toString())
        String message = updateService.addDetails(worklog)
        log.info("END :: UpdateController :: addEmployeeDetails :: worklog :: {}", worklog.toString())
        return message
    }

    @GetMapping("/viewDetails/{employee_name}")
    List<Worklog> viewLogByName(@PathVariable("employee_name") String employeeName){
        log.info("START :: UpdateController :: viewLogByName :: employeeName :: {}", employeeName)
        List<Worklog> worklogList = updateService.viewLogByName(employeeName)
        log.info("END :: UpdateController :: viewLogByName :: employeeName :: {}", employeeName)
        return worklogList
    }

}
