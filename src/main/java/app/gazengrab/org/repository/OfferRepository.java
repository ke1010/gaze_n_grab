package app.gazengrab.org.repository;

import app.gazengrab.org.model.Offers;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface OfferRepository extends MongoRepository<Offers, String> {

        List<Offers> findByIsActiveTrue();

}
