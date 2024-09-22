package com.example.messge.pojo;

import jakarta.persistence.*;
import lombok.Data;


import java.util.Date;

@Entity
@Data
@Table(name = "user_scores")
public class UserScore {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; // ID

    private Integer userId; // 用户ID

    @Column(length = 1)
    private String score; // 成绩，A、B、C、D、E

    @Temporal(TemporalType.TIMESTAMP)
    private Date assessmentDate; // 考核日期
}
