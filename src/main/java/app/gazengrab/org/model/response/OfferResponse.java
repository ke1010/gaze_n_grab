package app.gazengrab.org.model.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OfferResponse {

    private String title;
    private String description;
    private String category;
    private String imageUrl;
    private int discountPercent;
}
