package com.example.messge.Repository;

import com.example.messge.pojo.UserScore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserScoreRepository extends JpaRepository<UserScore, Integer> {
    List<UserScore> findByUserId(Integer userId);
}
