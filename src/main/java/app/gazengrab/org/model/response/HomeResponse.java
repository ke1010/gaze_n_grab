package app.gazengrab.org.model.response;

import app.gazengrab.org.model.Restaurant;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HomeResponse {
    private List<CategorySummary> categories;
    private List<RestaurantResponse> restaurants;

    @Data

    @Builder
    public static class CategorySummary {
        private String id;
        private String name;
        private String imageUrl;
        private long count;
    }
}
