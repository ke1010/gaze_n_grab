package app.gazengrab.org.service;


import app.gazengrab.org.model.User;
import app.gazengrab.org.repository.UserRepository;
import org.springframework.stereotype.Service;
import java.util.Random;

@Service
public class OtpService {
    private final UserRepository userRepository;

    public OtpService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String generateOtp(String phoneNumber) {
        String otp = String.format("%06d", new Random().nextInt(999999));
        User user = userRepository.findByPhoneNumber(phoneNumber).orElse(new User());
        user.setPhoneNumber(phoneNumber);
        user.setOtp(otp);
        user.setVerified(false);
        userRepository.save(user);

        return otp;
    }

    public boolean verifyOtp(String phoneNumber, String otp) {
        return userRepository.findByPhoneNumber(phoneNumber)
                .map(user -> {
                    if (otp.equals(user.getOtp())) {
                        user.setVerified(true);
                        user.setOtp(null);
                        userRepository.save(user);
                        return true;
                    }
                    return false;
                }).orElse(false);
    }
}

