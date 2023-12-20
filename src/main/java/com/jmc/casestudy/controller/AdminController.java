package com.jmc.casestudy.controller;

import com.jmc.casestudy.model.Bus;
import com.jmc.casestudy.payload.request.AddBusScheduleRequest;
import com.jmc.casestudy.payload.response.CommonResponse;
import com.jmc.casestudy.service.BusScheduleService;
import com.jmc.casestudy.service.BusService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdminController {

  @Autowired BusService busService;

  @Autowired BusScheduleService scheduleService;

  @PostMapping("/bus")
  public ResponseEntity<CommonResponse> addBus(@Valid @RequestBody Bus bus) {
    busService.add(bus);
    return ResponseEntity.ok(new CommonResponse("Bus added successfully"));
  }

  @PutMapping("/bus")
  public ResponseEntity<CommonResponse> editBus(@Valid @RequestBody Bus bus) {
    busService.edit(bus);
    return ResponseEntity.ok(new CommonResponse("Bus edited successfully"));
  }

  @DeleteMapping("/bus/{regNum}")
  public ResponseEntity<CommonResponse> deleteBus(@PathVariable String regNum) {
    busService.delete(regNum);
    return ResponseEntity.ok(new CommonResponse("Bus deleted successfully"));
  }

  @PostMapping("/schedule")
  public ResponseEntity<CommonResponse> addSchedule(
      @RequestBody AddBusScheduleRequest addScheduleRequest) {
    scheduleService.addBusSchedule(addScheduleRequest);
    return ResponseEntity.ok(new CommonResponse("Bus deleted successfully"));
  }
}
