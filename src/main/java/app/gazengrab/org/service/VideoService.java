package app.gazengrab.org.service;


import app.gazengrab.org.model.Restaurant;
import app.gazengrab.org.model.Video;
import app.gazengrab.org.model.response.VideoResponse;
import app.gazengrab.org.repository.RestaurantRepository;
import app.gazengrab.org.repository.VideoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VideoService {

    private final VideoRepository videoRepository;
    private final RestaurantRepository restaurantRepository;

    public List<VideoResponse> getActiveReels() {
        List<Video> reels = videoRepository.findByIsActiveTrueOrderByCreatedAtDesc();

        Set<String> restaurantIds = reels.stream()
                .map(Video::getRestaurantId)
                .collect(Collectors.toSet());

        List<Restaurant> restaurants = restaurantRepository.findAllById(restaurantIds);

        Map<String, Restaurant> restaurantMap = restaurants.stream()
                .collect(Collectors.toMap(Restaurant::getId, r -> r));

        return reels.stream().map(r -> {
            Restaurant restaurant = restaurantMap.get(r.getRestaurantId());
            return VideoResponse.builder()
                    .title(r.getTitle())
                    .videoUrl(r.getVideoUrl())
                    .thumbnailUrl(r.getThumbnailUrl())
                    .description(r.getDescription())
                    .restaurantId(r.getRestaurantId())
                    .name(restaurant != null ? restaurant.getName() : null)
                    .imageUrl(restaurant != null ? restaurant.getImageUrl() : null)
                    .rating(restaurant != null ? restaurant.getRating() : 0.0)
                    .address(restaurant != null ? restaurant.getAddress() : null)
                    .build();
        }).collect(Collectors.toList());
    }
}
