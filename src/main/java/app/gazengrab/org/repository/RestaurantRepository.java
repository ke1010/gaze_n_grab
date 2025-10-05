package app.gazengrab.org.repository;

import app.gazengrab.org.model.Restaurant;
import app.gazengrab.org.model.request.RestaurantRequest;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestaurantRepository extends MongoRepository<Restaurant, String> {
}