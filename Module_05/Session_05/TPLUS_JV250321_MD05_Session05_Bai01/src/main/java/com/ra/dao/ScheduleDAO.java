package com.ra.dao;

import com.ra.model.Schedule;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public interface ScheduleDAO {
    List<Schedule> getAllSchedule();

    Schedule getScheduleById(long id);

    boolean createSchedule(Schedule schedule);

    boolean updateSchedule(Schedule schedule);

    boolean deleteSchedule(long id);
}
