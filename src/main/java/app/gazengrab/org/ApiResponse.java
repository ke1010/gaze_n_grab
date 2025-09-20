package app.gazengrab.org;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
public  class ApiResponse<T> {
    private Boolean success;
    private String message;
    private T data;


    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SendOtpResponse{
        private String phoneNumber;
        private String otp;
    }


    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class VerifyOtpResponse{
        private String phoneNumber;
        private String otp;
        private Boolean verified;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public class Category {
        @Id
        private String id;
        private String name;
        private String iconUrl;
    }
}