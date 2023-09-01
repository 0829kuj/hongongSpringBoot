package com.example.firstproject.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Entity
@Table(name="members")
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)     // 자동생성. GenerationType.IDENTITY는 기본키 생성을 DB에 맞기는 전략.
    private Long id;
    @Column
    private String email;   // 이메일로 로그인
    @Column
    private String password;
    @Column
    private String name;

}
