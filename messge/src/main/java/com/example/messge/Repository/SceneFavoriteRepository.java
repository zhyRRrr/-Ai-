package com.example.messge.Repository;

import com.example.messge.pojo.SceneFavorite;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SceneFavoriteRepository extends JpaRepository<SceneFavorite, Long> {
    List<SceneFavorite> findByUserId(Integer userId); // 根据 userId 查找场景收藏
}
