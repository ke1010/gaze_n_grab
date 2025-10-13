package app.gazengrab.org.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.time.LocalDateTime;

@Data
@Document(collection = "deals")
public class Offers {
    @Id
    private String id;

    private String title;
    private String description;
    private String category;
    private String imageUrl;
    private int discountPercent;

    private String restaurantId;
    private Instant validFrom;
    private Instant validUntil;
    private boolean isActive;
}
