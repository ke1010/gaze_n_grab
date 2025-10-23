package app.gazengrab.org.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Data
@Document(collection = "videos")
public class Video {

        @Id
        private String id;

        private String title;
        private String videoUrl;
        private String thumbnailUrl;
        private String description;
        private String restaurantId;
        private boolean isActive;
        private Instant createdAt;
    }

