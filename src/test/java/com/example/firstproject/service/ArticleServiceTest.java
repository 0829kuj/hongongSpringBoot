package com.example.firstproject.service;

import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.entity.Article;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class ArticleServiceTest {

    @Autowired
    ArticleService articleService;

    @Test
    void show() {
        // 1. 예상 데이터 작성하기
        Long id = 1L;
        Article expected = new Article(id, "첫번쨰 게시글", "첫 게시글의 내용입니다.");
        // 2. 실제 데이터 획득하기
        Article article = articleService.show(id);
        // 3. 예상 데이터와 실제 데이터 비교해 검증하기
        assertEquals(expected.toString(), article.toString());
    }

    @Test
    void show_fail() {
        Long id = -1L;      // 존재하지 않는 데이터
        Article expected = null;
        Article article = articleService.show(id);
        assertEquals(expected.toString(), article.toString());
    }

    @Test
    @Transactional
    void create() {
        String title = "가나다라";
        String content = "마바사아자차카타파하";
        ArticleForm dto = new ArticleForm(null, title, content);
        Article expected = new Article(43L, title, content);
        // 실제 데이터
        Article article = articleService.create(dto);
        // 비교 및 검증
        assertEquals(expected.toString(), article.toString());
    }

//    @Test
//    @Transactional
//    void update() {
//    }
//
//    @Test
//    void delete() {
//    }
}