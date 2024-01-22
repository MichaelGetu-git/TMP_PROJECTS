package CorrectedCode;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class RouteList {
    private static List<FlightRoute> routes = new ArrayList<>();

    public RouteList() {
        addDefaultRoute();
    }

    private void addDefaultRoute() {
        routes.add(new FlightRoute("DefaultFrom", "DefaultTo", 500.0, 1.5, "DefaultPath", "DefaultSchedule"));
    }

    public static void addRoute(String from, String to, double distance, double time, String path, String schedule) {
        FlightRoute newRoute = new FlightRoute(from, to, distance, time, path, schedule);
        routes.add(newRoute);
    }

    public static List<FlightRoute> getRoutes() {
        return routes;
    }

    
    
    public static FlightRoute findRouteById(List<FlightRoute> routes, int routeId) {
        for (FlightRoute route : routes) {
            if (route.getRouteId() == routeId) {
                return route;
            }
        }
        return null;
    }
    
    protected static void displayRoutes(List<FlightRoute> routes) {
    System.out.println("Available Routes:");
    for (FlightRoute route : routes) {
        System.out.println(route); // Assuming FlightRoute has a meaningful toString() method
        }
    }
     
    public static FlightRoute searchRoute(int routeId) {
        for (FlightRoute route : routes) {
            if (route.getRouteId() == routeId) {
                return route;
            }
        }
        return null;
    }
    
    

    public static boolean deleteRoute(int routeId) {
        FlightRoute routeToDelete = searchRoute(routeId);
        if (routeToDelete != null) {
            routes.remove(routeToDelete);
            System.out.println("Route deleted successfully.");
            return true;
        } else {
            System.out.println("Route not found.");
            return false;
        }
    }
   public static boolean updateRoute(int routeId, String newFrom, String newTo,
                                  double newDistance, double newTime,
                                  String newPath, String newSchedule) {
    FlightRoute routeToUpdate = searchRoute(routeId);
    if (routeToUpdate != null) {
        // Update the route information
        routeToUpdate.setFrom(newFrom);
        routeToUpdate.setTo(newTo);
        routeToUpdate.setFlightDistance(newDistance);
        routeToUpdate.setFlightTime(newTime);
        routeToUpdate.setFlightPath(newPath);
        routeToUpdate.setFlightSchedule(newSchedule);

        System.out.println("Route updated successfully.");
        return true;
    } else {
        System.out.println("Route not found.");
        return false;
    }
}
}
                                          

