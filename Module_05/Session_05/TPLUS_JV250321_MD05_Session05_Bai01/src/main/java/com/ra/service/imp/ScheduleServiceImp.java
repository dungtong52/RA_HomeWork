package com.ra.service.imp;

import com.ra.dao.ScheduleDAO;
import com.ra.dao.imp.ScheduleDAOImp;
import com.ra.model.Schedule;
import com.ra.service.ScheduleService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public class ScheduleServiceImp implements ScheduleService {
    private final ScheduleDAO scheduleDAO;

    public ScheduleServiceImp() {
        scheduleDAO = new ScheduleDAOImp();
    }

    @Override
    public List<Schedule> getAllSchedule() {
        return scheduleDAO.getAllSchedule();
    }

    @Override
    public Schedule getScheduleById(long id) {
        return scheduleDAO.getScheduleById(id);
    }

    @Override
    public boolean createSchedule(Schedule schedule) {
        return scheduleDAO.createSchedule(schedule);
    }

    @Override
    public boolean updateSchedule(Schedule schedule) {
        return scheduleDAO.updateSchedule(schedule);
    }

    @Override
    public boolean deleteSchedule(long id) {
        return scheduleDAO.deleteSchedule(id);
    }
}
