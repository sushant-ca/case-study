package com.jmc.casestudy;

import com.jmc.casestudy.controller.AuthController;
import com.jmc.casestudy.model.Route;
import com.jmc.casestudy.payload.request.UserSignupRequest;
import com.jmc.casestudy.service.RouteService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Java Master Class Case Study"))
public class CaseStudyApplication implements CommandLineRunner {

  @Autowired AuthController authController;

  @Autowired RouteService routeService;

  public static void main(String[] args) {
    SpringApplication.run(CaseStudyApplication.class, args);
  }

  @Override
  public void run(String... args) throws Exception {
    authController.registerUser(
        new UserSignupRequest("admin", "admin@gmail.com", "admin", "4c782eB4278aC"));

    routeService.addRoute(new Route(1L, "Hosur", "Bangalore"));
    routeService.addRoute(new Route(2L, "Bangalore", "Hosur"));
    routeService.addRoute(new Route(3L, "Chennai", "Bangalore"));
    routeService.addRoute(new Route(4L, "Bangalore", "Chennai"));
  }
}
