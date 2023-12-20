package com.jmc.casestudy.payload.response;

import java.sql.Time;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetRouteDetailsResponse {
  private Time startTime;

  private Time endTime;

  private String busRegistrationNumber;

  private String startLocation;

  private String endLocation;
}
