package com.jmc.casestudy.service;

import com.jmc.casestudy.model.BusSchedule;
import com.jmc.casestudy.payload.request.AddBusScheduleRequest;
import com.jmc.casestudy.payload.response.GetRouteDetailsResponse;
import java.util.List;

public interface BusScheduleService {

  void addBusSchedule(AddBusScheduleRequest addScheduleRequest);

  List<BusSchedule> getAllBusSchedule();

  List<GetRouteDetailsResponse> getScheduleByRouteId(Long routeId);
}
