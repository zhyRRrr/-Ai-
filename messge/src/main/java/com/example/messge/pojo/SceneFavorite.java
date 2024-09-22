package com.example.messge.pojo;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "scene_favorites")
public class SceneFavorite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int trainId; // 训练难度
    private int sceneId; // 场景号
    private String sceneName; // 场景名
    private Integer userId; // 用户id
}
