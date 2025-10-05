package app.gazengrab.org.controller;

import app.gazengrab.org.ApiResponse;
import app.gazengrab.org.model.response.SendOtpResponse;
import app.gazengrab.org.model.response.VerifyOtpResponse;
import app.gazengrab.org.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/otp")
public class AuthController {
    private final AuthService otpService;

    public AuthController(AuthService otpService) {
        this.otpService = otpService;
    }

    @PostMapping("/send")
    public ResponseEntity<ApiResponse<SendOtpResponse>> sendOtp(@RequestParam String phoneNumber) {
        String otp = otpService.generateOtp(phoneNumber);
        SendOtpResponse sendOtpResponse = new SendOtpResponse(phoneNumber, otp);
        ApiResponse<SendOtpResponse> response = new ApiResponse<>(true, "OTP sent successfully", sendOtpResponse);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/verify")
    public ResponseEntity<ApiResponse<VerifyOtpResponse>> verifyOtp(@RequestParam String phoneNumber, @RequestParam String otp) {
        Boolean verified = otpService.verifyOtp(phoneNumber, otp);
        VerifyOtpResponse verifyResponse = new VerifyOtpResponse(phoneNumber, otp, verified);
        ApiResponse<VerifyOtpResponse> response = new ApiResponse<>(true, "otpVerified", verifyResponse);
        return ResponseEntity.ok(response);
    }
}

