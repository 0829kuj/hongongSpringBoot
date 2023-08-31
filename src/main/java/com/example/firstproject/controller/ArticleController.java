package com.example.firstproject.controller;

import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.entity.Article;
import com.example.firstproject.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;

@Slf4j
@Controller
public class ArticleController {
    @Autowired
    private ArticleRepository articleRepository;

    @GetMapping("/articles/new")
    public String newArticleForm() {
        return "articles/new";
    }

    @PostMapping("/articles/create")
    public String createAricle(ArticleForm form) {
        log.info(form.toString());
        // 1. DTO를 entity로 변환
        Article article = form.toEntity();
        System.out.println(article.toString());

        // 2. repository로 entity를 DB에 저장
        Article saved = articleRepository.save(article);
        log.info(saved.toString());
        return "redirect:/articles/" + saved.getId();
    }

    @GetMapping("/articles/{id}")
    public String show(@PathVariable Long id, Model m){
        log.info("id = " + id);
        // 1. DB에서 데이터 조회해 가져오기
        Optional<Article> articleEntity = articleRepository.findById(id);
        // 2. model에 데이터 등록하기
        m.addAttribute("article", articleEntity.get());
        // 3. 뷰 페이지 반환하기
        return "articles/show";
    }

    @GetMapping("/articles")
    public String showAll(Model m){
        // 1. DB에서 모든 데이터 가져오기
        List<Article> articleEntityList = articleRepository.findAll();
        // 2. model에 데이터 등록하기
        m.addAttribute("articleList", articleEntityList);
        // 3. 뷰 페이지 반환하기
        return "articles/index";
    }

    @GetMapping("/articles/{id}/edit")
    public String edit(){
        return "articles/edit";
    }
}
