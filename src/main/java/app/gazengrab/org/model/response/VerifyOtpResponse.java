package app.gazengrab.org.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VerifyOtpResponse {


        private String phoneNumber;
        private String otp;
        private Boolean verified;
    }

