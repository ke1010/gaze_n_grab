package app.gazengrab.org.controller;

import app.gazengrab.org.ApiResponse;
import app.gazengrab.org.model.request.RestaurantRequest;
import app.gazengrab.org.model.response.RestaurantResponse;
import app.gazengrab.org.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@RestController
@RequestMapping("/api/restaurants")
@RequiredArgsConstructor
public class RestaurantController {

    private final RestaurantService restaurantService;

    @PostMapping("/recommended")
    public ResponseEntity<ApiResponse<List<RestaurantResponse>>> getNearbyRestaurants(
            @RequestBody RestaurantRequest request) {

        double defaultRadiusKm = 5.0;

        List<RestaurantResponse> restaurants = restaurantService.getNearbyRestaurants(
                request.getLatitude(),
                request.getLongitude(),
                defaultRadiusKm
        );

        return ResponseEntity.ok(ApiResponse.success("Nearby restaurants found", restaurants));
    }
}
