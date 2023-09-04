//package com.example.firstproject.persistence;
//
//import com.example.firstproject.entity.Article;
//import com.example.firstproject.repository.ArticleRepository;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Pageable;
//
//@SpringBootTest
//class ArticleRepositoryTest {
//
//    @Autowired
//    ArticleRepository articleRepository;
//
//    @Test
//    public void testPageDefault() {
//
//        Pageable pageable = PageRequest.of(0, 7);
//
//        Page<Article> result = articleRepository.findAll(pageable);
//
//        System.out.println(result);
//    }
//
//}