package com.ra.service;

import com.ra.model.Schedule;

import java.util.List;

public interface ScheduleService {
    List<Schedule> getAllSchedule();

    Schedule getScheduleById(long id);

    boolean createSchedule(Schedule schedule);

    boolean updateSchedule(Schedule schedule);

    boolean deleteSchedule(long id);
}
