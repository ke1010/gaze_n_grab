package app.gazengrab.org.controller;

import app.gazengrab.org.ApiResponse;
import app.gazengrab.org.model.response.VideoResponse;
import app.gazengrab.org.service.VideoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/reels")
@RequiredArgsConstructor
public class VideoController {

    private final VideoService videoService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<VideoResponse>>> getActiveReels() {
        return ResponseEntity.ok(ApiResponse.success("videos fetched", videoService.getActiveReels()));
    }
}
