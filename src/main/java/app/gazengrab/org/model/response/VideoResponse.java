package app.gazengrab.org.model.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class VideoResponse {
    private String title;
    private String videoUrl;
    private String thumbnailUrl;
    private String description;
    private String restaurantId;

    private String name;
    private String imageUrl;
    private double rating;
    private String address;
}
