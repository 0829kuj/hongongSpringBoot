package com.example.firstproject.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Entity
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)     // 자동생성. GenerationType.IDENTITY는 기본키 생성을 DB에 맞기는 전략.
    private Long id;
    @Column
    private String title;
    @Column
    private String content;

    public void patch(Article article) {
        // 수정할 내용이 없는 경우 null대신 기존의 Article객체가 가진 값으로 대체함 
        if (article.title !=null){
            this.title = article.title;    
        }
        if (article.content !=null){
            this.content = article.content;
        }
    }
}
