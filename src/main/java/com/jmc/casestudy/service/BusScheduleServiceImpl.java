package com.jmc.casestudy.service;

import com.jmc.casestudy.model.*;
import com.jmc.casestudy.payload.request.AddBusScheduleRequest;
import com.jmc.casestudy.payload.response.GetRouteDetailsResponse;
import com.jmc.casestudy.repository.BusScheduleRepository;
import java.sql.Time;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BusScheduleServiceImpl implements BusScheduleService {

  @Autowired BusService busService;

  @Autowired RouteService routeService;

  @Autowired BusScheduleRepository scheduleRepository;

  @Override
  public void addBusSchedule(AddBusScheduleRequest addScheduleRequest) {
    Optional<Bus> bus = busService.findByRegNum(addScheduleRequest.getBusRegNum());
    Optional<Route> route = routeService.getById(addScheduleRequest.getRouteId());
    if (bus.isPresent() && route.isPresent()) {
      List<BusSchedule> scheduleList = scheduleRepository.findByBus(bus.get());
      if (isTimeRangeAvailable(
          addScheduleRequest.getStartTime(), addScheduleRequest.getEndTime(), scheduleList)) {
        scheduleRepository.save(
            new BusSchedule(
                null,
                addScheduleRequest.getStartTime(),
                addScheduleRequest.getEndTime(),
                bus.get(),
                route.get()));
      } else {
        throw new RuntimeException("Time is not available for the bus");
      }
    } else {
      throw new RuntimeException("Either Bus or Route is not valid");
    }
  }

  private boolean isTimeRangeAvailable(
      LocalTime newStartTime, LocalTime newEndTime, List<BusSchedule> existingSchedules) {
    for (BusSchedule existingSchedule : existingSchedules) {
      if (isOverlap(
          newStartTime,
          newEndTime,
          existingSchedule.getStartTime(),
          existingSchedule.getEndTime())) {
        return false;
      }
    }
    return true;
  }

  private boolean isOverlap(LocalTime start1, LocalTime end1, LocalTime start2, LocalTime end2) {
    return !start1.isAfter(end2) && !end1.isBefore(start2);
  }

  @Override
  public List<BusSchedule> getAllBusSchedule() {
    return scheduleRepository.findAll();
  }

  @Override
  public List<GetRouteDetailsResponse> getScheduleByRouteId(Long routeId) {
    List<GetRouteDetailsResponse> routeDetailsResponseList = new ArrayList<>();
    for (Object[] row : scheduleRepository.getRouteDetails(routeId)) {
      GetRouteDetailsResponse routeDetails = new GetRouteDetailsResponse();
      routeDetails.setStartTime((Time) row[0]);
      routeDetails.setEndTime((Time) row[1]);
      routeDetails.setBusRegistrationNumber((String) row[2]);
      routeDetails.setStartLocation((String) row[3]);
      routeDetails.setEndLocation((String) row[4]);

      routeDetailsResponseList.add(routeDetails);
    }
    return routeDetailsResponseList;
  }
}
