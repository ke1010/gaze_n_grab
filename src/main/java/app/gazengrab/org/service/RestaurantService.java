package app.gazengrab.org.service;

import app.gazengrab.org.model.Restaurant;
import app.gazengrab.org.model.request.RestaurantRequest;
import app.gazengrab.org.model.response.RestaurantResponse;
import app.gazengrab.org.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;



@Service
@RequiredArgsConstructor
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;

    private double calculateDistance(double lat1, double lon1, double lat2, double lon2) {
        double R = 6371; // Earth radius in km
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(dLon / 2) * Math.sin(dLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return R * c;
    }

    public List<RestaurantResponse> getNearbyRestaurants(double lat, double lng, double radiusKm) {
        List<Restaurant> allRestaurants = restaurantRepository.findAll();

        return allRestaurants.stream()
                .filter(r -> calculateDistance(lat, lng, r.getLatitude(), r.getLongitude()) <= radiusKm)
                .sorted(Comparator.comparingDouble(Restaurant::getRating).reversed())
                .map(r -> RestaurantResponse.builder()
                        .id(r.getId())
                        .name(r.getName())
                        .rating(r.getRating())
                        .imageUrl(r.getImageUrl())
                        .build())
                .collect(Collectors.toList());
    }
}
