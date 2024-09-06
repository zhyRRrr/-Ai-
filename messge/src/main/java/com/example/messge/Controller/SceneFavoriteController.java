package com.example.messge.Controller;

import com.example.messge.Service.SceneFavoriteService;
import com.example.messge.pojo.Chat;
import com.example.messge.pojo.SceneFavorite;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/scene-favorites")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class SceneFavoriteController {
    private final SceneFavoriteService sceneFavoriteService;

    @PostMapping("/add")
    public SceneFavorite addSceneFavorite(@RequestParam int trainId,
                                          @RequestParam int sceneId,
                                          @RequestParam String sceneName) {
        return sceneFavoriteService.createSceneFavorite(trainId, sceneId, sceneName);
    }

    @DeleteMapping("/{id}")
    public void deleteSceneFavorite(@PathVariable Long id) {
        sceneFavoriteService.deleteSceneFavorite(id);
    }

    @GetMapping("/")
    public List<SceneFavorite> getAllFavorites() {
        return sceneFavoriteService.getAllFavorites();
    }

    @PostMapping("/enter/{id}")
    public Chat enterFavoriteScene(@PathVariable Long id) {
        return sceneFavoriteService.enterFavoriteScene(id);
    }

}
