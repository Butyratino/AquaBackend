package com.sergio.bdas2.backend.controller;

import com.sergio.bdas2.backend.model.dto.SchedulesDto;
import com.sergio.bdas2.backend.service.SchedulesService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/schedules")
public class SchedulesController {

    private final SchedulesService schedulesService;

    public SchedulesController(SchedulesService schedulesService) {
        this.schedulesService = schedulesService;
    }

    @GetMapping("/all")
    public List<SchedulesDto> getAllSchedules() {
        return schedulesService.getAllSchedules();
    }

    @GetMapping("/{scheduleId}")
    public ResponseEntity<SchedulesDto> getScheduleById(@PathVariable Long scheduleId) {
        SchedulesDto schedule = schedulesService.getScheduleById(scheduleId);
        return new ResponseEntity<>(schedule, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<String> addSchedule(@RequestBody SchedulesDto schedule) {
        schedulesService.addSchedule(schedule);
        return new ResponseEntity<>("Schedule added successfully", HttpStatus.CREATED);
    }

    @PutMapping("/update/{scheduleId}")
    public ResponseEntity<Void> updateSchedule(@PathVariable Long scheduleId, @RequestBody SchedulesDto schedule) {
        schedule.setScheduleId(scheduleId);
        schedulesService.updateSchedule(scheduleId, schedule);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/delete/{scheduleId}")
    public ResponseEntity<Void> deleteSchedule(@PathVariable Long scheduleId) {
        schedulesService.deleteSchedule(scheduleId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
