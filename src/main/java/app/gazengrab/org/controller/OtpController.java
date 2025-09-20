package app.gazengrab.org.controller;


import app.gazengrab.org.ApiResponse;
import app.gazengrab.org.service.OtpService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/otp")
public class OtpController {
    private final OtpService otpService;

    public OtpController(OtpService otpService) {
        this.otpService = otpService;
    }

    @PostMapping("/send")
    public ResponseEntity<ApiResponse<ApiResponse.SendOtpResponse>> sendOtp(@RequestParam String phoneNumber) {
        String otp = otpService.generateOtp(phoneNumber);
        ApiResponse.SendOtpResponse sendOtpResponse = new ApiResponse.SendOtpResponse(phoneNumber, otp)
        ApiResponse<ApiResponse.SendOtpResponse> response = new ApiResponse<>(true, "OTP sent successfully", sendOtpResponse);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/verify")
    public ResponseEntity<ApiResponse<ApiResponse.VerifyOtpResponse>> verifyOtp(@RequestParam String phoneNumber, @RequestParam String otp) {
        Boolean verified = otpService.verifyOtp(phoneNumber, otp);
        ApiResponse.VerifyOtpResponse verifyResponse = new ApiResponse.VerifyOtpResponse(phoneNumber, otp, verified);
        ApiResponse<ApiResponse.VerifyOtpResponse> response = new ApiResponse<>(true, "otpVerified", verifyResponse);
        return ResponseEntity.ok(response);
    }
}
