package app.gazengrab.org.repository;


import app.gazengrab.org.model.request.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.Optional;

public interface  UserRepository extends MongoRepository<User, String> {
    Optional<User> findByPhoneNumber(String phoneNumber);
}
