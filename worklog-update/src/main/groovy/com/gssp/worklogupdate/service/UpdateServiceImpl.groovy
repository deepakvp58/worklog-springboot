package com.gssp.worklogupdate.service

import com.gssp.worklogupdate.model.Login
import com.gssp.worklogupdate.model.Worklog
import com.gssp.worklogupdate.repository.WorklogRepository
import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Slf4j
@Service
class UpdateServiceImpl implements UpdateService {

    @Autowired
    WorklogRepository worklogRepository

    @Override
    Boolean loginDetails(Login login) {
        log.info("START :: UpdateServiceImpl :: loginDetails :: login :: {}", login.toString())
        Boolean isLoggedIn = false
        if(login.getUserName().equals('admin@mail.com') && login.getPassword().equals('admin')) {
            log.info("INSIDE :: UpdateServiceImpl :: loginDetails :: if :: credentials checking")
            isLoggedIn = true
        }
        log.info("END :: UpdateServiceImpl :: loginDetails :: login :: {}", login.toString())
        return isLoggedIn
    }

    @Override
    String addDetails(Worklog worklog) {
        log.info("START :: UpdateServiceImpl :: addDetails :: worklog :: {}", worklog.toString())
        worklog.setLoggedDate(new Date())
        worklogRepository.save(worklog)
        log.info("END :: UpdateServiceImpl :: addDetails :: worklog :: {}", worklog.toString())
        return "Log added successfully."
    }

    @Override
    List<Worklog> viewLogByName(String employeeName) {
        log.info("START :: UpdateServiceImpl :: viewLogByName :: employeeName :: {}", employeeName)
        List<Worklog> worklogList = worklogRepository.findByEmployeeName(employeeName)
        log.info("END :: UpdateServiceImpl :: viewLogByName :: employeeName :: {}", employeeName)
        return worklogList
    }
}
