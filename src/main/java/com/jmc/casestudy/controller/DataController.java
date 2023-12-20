package com.jmc.casestudy.controller;

import com.jmc.casestudy.model.Bus;
import com.jmc.casestudy.model.BusSchedule;
import com.jmc.casestudy.model.Route;
import com.jmc.casestudy.payload.response.GetRouteDetailsResponse;
import com.jmc.casestudy.service.BusScheduleService;
import com.jmc.casestudy.service.BusService;
import com.jmc.casestudy.service.RouteService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/data")
public class DataController {
  @Autowired BusService busService;

  @Autowired RouteService routeService;

  @Autowired BusScheduleService scheduleService;

  @GetMapping("/bus")
  public ResponseEntity<List<Bus>> getAllBus() {
    return ResponseEntity.ok(busService.getAllBus());
  }

  @GetMapping("/routes")
  public ResponseEntity<List<Route>> getAllRoutes() {
    return ResponseEntity.ok(routeService.getAllRoute());
  }

  @GetMapping("/schedule")
  public ResponseEntity<List<BusSchedule>> getAllSchedule() {
    return ResponseEntity.ok(scheduleService.getAllBusSchedule());
  }

  @GetMapping("/schedule/{routeId}")
  public ResponseEntity<List<GetRouteDetailsResponse>> getScheduleByRouteId(
      @PathVariable Long routeId) {
    return ResponseEntity.ok(scheduleService.getScheduleByRouteId(routeId));
  }
}
