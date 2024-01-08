package com.sergio.bdas2.backend.service;

import com.sergio.bdas2.backend.model.dto.LogDto;
import com.sergio.bdas2.backend.model.entity.TableColumn;
import com.sergio.bdas2.backend.repository.LogDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class LogService {

    private final LogDao logDAO;

    @Autowired
    public LogService(LogDao logDAO) {
        this.logDAO = logDAO;
    }

    public List<TableColumn> getTableColumns() {
        return logDAO.getTableColumns();
    }

    public List<LogDto> getAllLogs() {
        return logDAO.getAllLogs();
    }







}
