package app.gazengrab.org.controller;

import app.gazengrab.org.ApiResponse;
import app.gazengrab.org.model.request.LocationRequest;
import app.gazengrab.org.model.response.HomeResponse;
import app.gazengrab.org.model.response.SendOtpResponse;
import app.gazengrab.org.service.HomeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/home")
@RequiredArgsConstructor
public class HomeController {

    private final HomeService homeService;

    @PostMapping
    public ResponseEntity<ApiResponse<HomeResponse>> getHomeData(@RequestBody LocationRequest request) {
        HomeResponse homeResponse = homeService.getHomeData(request);
        ApiResponse<HomeResponse> response = new ApiResponse<>(true, "Restaurants", homeResponse);
        return ResponseEntity.ok(response);
    }
}
