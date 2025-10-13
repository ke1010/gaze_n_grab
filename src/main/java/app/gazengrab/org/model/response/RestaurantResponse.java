package app.gazengrab.org.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RestaurantResponse {

    @Id
    private String id;
    private String name;
    private double rating;
    private String imageUrl;
    private String address;
    private double latitude;
    private double longitude;
    private double distanceKm;
}
