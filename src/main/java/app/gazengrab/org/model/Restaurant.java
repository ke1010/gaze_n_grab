package app.gazengrab.org.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document(collection = "restaurants")
public class Restaurant {
    @Id
    private String id;
    private String name;
    private double rating;
    private double latitude;
    private double longitude;
    private String address;
    private String imageUrl;
    private List<String> categoryIds;
    @Transient
    private double distanceKm;
}
