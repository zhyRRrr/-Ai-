package com.example.messge.Service;

import com.example.messge.Repository.SceneFavoriteRepository;
import com.example.messge.pojo.Chat;
import com.example.messge.pojo.SceneFavorite;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SceneFavoriteService {
    private final SceneFavoriteRepository sceneFavoriteRepository;
    @Autowired
    private ChatService chatService;

    public SceneFavorite createSceneFavorite(int trainId, int sceneId, String sceneName) {
        SceneFavorite favorite = new SceneFavorite();
        favorite.setTrainId(trainId);
        favorite.setSceneId(sceneId);
        favorite.setSceneName(sceneName);
        return sceneFavoriteRepository.save(favorite);
    }

    public void deleteSceneFavorite(Long id) {
        sceneFavoriteRepository.deleteById(id);
    }

    public List<SceneFavorite> getAllFavorites() {
        return sceneFavoriteRepository.findAll();
    }

    public Chat enterFavoriteScene(Long favoriteId) {
        SceneFavorite favorite = sceneFavoriteRepository.findById(favoriteId)
                .orElseThrow(() -> new RuntimeException("场景收藏未找到"));

        // 使用ChatService创建聊天
        Chat chat = chatService.createChat(favorite.getSceneName(), favorite.getTrainId(), favorite.getSceneId());
        return chat;
    }

}
