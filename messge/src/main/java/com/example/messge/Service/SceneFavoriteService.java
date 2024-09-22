package com.example.messge.Service;

import com.example.messge.Repository.SceneFavoriteRepository;
import com.example.messge.pojo.Chat;
import com.example.messge.pojo.SceneFavorite;
import com.example.messge.utils.ThreadLocalUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class SceneFavoriteService {
    private final SceneFavoriteRepository sceneFavoriteRepository;
    @Autowired
    private ChatService chatService;

    public SceneFavorite createSceneFavorite(int trainId, int sceneId, String sceneName, Integer userId) {
        SceneFavorite favorite = new SceneFavorite();
        favorite.setTrainId(trainId);
        favorite.setSceneId(sceneId);
        favorite.setSceneName(sceneName);
        favorite.setUserId(userId); // 设置 userId
        return sceneFavoriteRepository.save(favorite);
    }

    public void deleteSceneFavorite(Long id) {
        sceneFavoriteRepository.deleteById(id);
    }

    public List<SceneFavorite> getAllFavoritesByUserId() {
        // 需要在 SceneFavoriteRepository 中添加方法以根据 userId 查找场景收藏
        Map<String, Object> map = ThreadLocalUtil.get();
        Integer userId = (Integer) map.get("id");
        return sceneFavoriteRepository.findByUserId(userId);
    }

    public Chat enterFavoriteScene(Long favoriteId) {
        SceneFavorite favorite = sceneFavoriteRepository.findById(favoriteId)
                .orElseThrow(() -> new RuntimeException("场景收藏未找到"));

        // 使用ChatService创建聊天
        Chat chat = chatService.createChat(favorite.getSceneName(), favorite.getTrainId(), favorite.getSceneId(), favorite.getUserId());
        return chat;
    }

}
