package com.sergio.bdas2.backend.service;

import com.sergio.bdas2.backend.model.dto.SchedulesDto;
import com.sergio.bdas2.backend.repository.SchedulesDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SchedulesService {

    private final SchedulesDao schedulesDao;

    @Autowired
    public SchedulesService(SchedulesDao schedulesDao) {
        this.schedulesDao = schedulesDao;
    }

    public List<SchedulesDto> getAllSchedules() {
        return schedulesDao.getAllSchedules();
    }

    public SchedulesDto getScheduleById(Long scheduleId) {
        return schedulesDao.getScheduleById(scheduleId);
    }

    public void updateSchedule(Long id, SchedulesDto schedule) {
        schedulesDao.updateSchedule(id, schedule);
    }

    public void addSchedule(SchedulesDto schedule) {
        schedulesDao.addSchedule(schedule);
    }

    public void deleteSchedule(Long scheduleId) {
        schedulesDao.deleteSchedule(scheduleId);
    }
}
