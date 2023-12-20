# case-study
 JMC Case Study

Steps to run the program :

1 ) This is a Maven Project Clone the project and build this using mvn clean package

2 ) Then create a mysql database named - testdb_spring

3 ) Then create a procedure by running the below query in mysql 

    CREATE PROCEDURE GetRouteDetails(IN routeId INT)

    BEGIN

    SELECT
    
        s.start_time AS "Start Time",
        
        s.end_time AS "End Time",
        
        b.reg_num AS "Bus Registration Number",
        
        r.start_location AS "Start Location",
        
        r.end_location AS "End Location"
        
    FROM
    
        Schedule s
        
    LEFT JOIN
    
        Bus b ON s.bus_id = b.id
        
    LEFT JOIN
    
        Route r ON s.route_id = r.id
        
    WHERE
    
        r.id = routeId
        
    ORDER BY
    
        s.start_time;
        
    END

4 ) Then start the application ( application will be started in default port 8080 ) 

5 ) In the start of application a new user of admin will be automatically created with username - admin and password - 4c782eB4278aC.

6 ) you can use this user credentials to add new users, bus or schedule by refering the below swagger

7 ) The Swagger UI URL is - http://localhost:8080/swagger-ui/index.html#/

8 ) You can either create a user as role user or admin

9 ) user role will have access to all endpoints with user/* pattern

10 ) admin role will have access to all the endpoints in the system

11 ) for accessing every API you need to provide authorization by loging in the swagger by clicking on authrize button and enter the JWT given in the response of api/auth/signin API.

#Have Fun
