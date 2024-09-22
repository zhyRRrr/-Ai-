package com.example.messge.Controller;

import com.example.messge.Service.SceneFavoriteService;
import com.example.messge.pojo.Chat;
import com.example.messge.pojo.SceneFavorite;
import com.example.messge.utils.ThreadLocalUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/scene-favorites")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class SceneFavoriteController {
    private final SceneFavoriteService sceneFavoriteService;

    @PostMapping("/add")
    public SceneFavorite addSceneFavorite(@RequestParam int trainId,
                                          @RequestParam int sceneId,
                                          @RequestParam String sceneName
                                          ) {
       Map<String,Object>map = ThreadLocalUtil.get();// 添加 userId 参数
        Integer userId = (Integer) map.get("id");
        return sceneFavoriteService.createSceneFavorite(trainId, sceneId, sceneName, userId);
    }

    @DeleteMapping("/{id}")
    public void deleteSceneFavorite(@PathVariable Long id) {
        sceneFavoriteService.deleteSceneFavorite(id);
    }

    @GetMapping("/")
    public List<SceneFavorite> getAllFavorites() { // 添加 userId 参数
        return sceneFavoriteService.getAllFavoritesByUserId(); // 根据 userId 获取收藏
    }

    @PostMapping("/enter/{id}")
    public Chat enterFavoriteScene(@PathVariable Long id) {
        return sceneFavoriteService.enterFavoriteScene(id);
    }

}
