package app.gazengrab.org.service;


import app.gazengrab.org.model.Category;
import app.gazengrab.org.model.Restaurant;
import app.gazengrab.org.model.request.LocationRequest;
import app.gazengrab.org.model.response.HomeResponse;
import app.gazengrab.org.repository.CategoryRepository;
import app.gazengrab.org.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HomeService {

    private final RestaurantRepository restaurantRepository;
    private final CategoryRepository categoryRepository;

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

    public HomeResponse getHomeData(LocationRequest req) {
        List<Restaurant> allRestaurants = restaurantRepository.findAll();

        List<Restaurant> nearbyRestaurants = allRestaurants.stream()
                .filter(r -> calculateDistance(req.getLatitude(), req.getLongitude(),
                        r.getLatitude(), r.getLongitude()) <= req.getRadiusKm())
                .collect(Collectors.toList());

        Map<String, Long> categoryCountMap = nearbyRestaurants.stream()
                .filter(r -> r.getCategoryIds() != null)
                .flatMap(r -> r.getCategoryIds().stream())
                .collect(Collectors.groupingBy(id -> id, Collectors.counting()));

        List<Category> allCategories = categoryRepository.findAllById(categoryCountMap.keySet());

        List<HomeResponse.CategorySummary> categorySummaries = allCategories.stream()
                .map(cat -> HomeResponse.CategorySummary.builder()
                        .id(cat.getId())
                        .name(cat.getName())
                        .imageUrl(cat.getImageUrl())
                        .count(categoryCountMap.getOrDefault(cat.getId(), 0L))
                        .build())
                .collect(Collectors.toList());

        return HomeResponse.builder()
                .categories(categorySummaries)
                .restaurants(nearbyRestaurants)
                .build();
    }
}
