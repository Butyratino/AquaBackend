package com.sergio.bdas2.backend.controller;

import com.sergio.bdas2.backend.model.dto.LogDto;
import com.sergio.bdas2.backend.model.entity.TableColumn;
import com.sergio.bdas2.backend.service.LogService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/aquapark")
public class AquaparkController {

    private final LogService logService;

    public AquaparkController(LogService logService) {
        this.logService = logService;
    }

    @GetMapping("/logs")
    public List<LogDto> getAllLogs() {
        return logService.getAllLogs();
    }

    @GetMapping("/tables/columns")
    public List<TableColumn> getTableColumns() {
        return logService.getTableColumns();
    }
}
