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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    public String createAricle(ArticleForm dto) {
        log.info(dto.toString());
        // 1. DTO를 entity로 변환
        Article article = dto.toEntity();
        System.out.println(article.toString());

        // 2. repository로 entity를 DB에 저장
        Article saved = articleRepository.save(article);
        log.info(saved.toString());
        return "redirect:/articles/" + saved.getId();
    }

    @GetMapping("/articles/{id}")
    public String show(@PathVariable Long id, Model m) {
        log.info("id = " + id);
        // 1. DB에서 데이터 조회해 가져오기
        Optional<Article> articleEntity = articleRepository.findById(id);
        // 2. model에 데이터 등록하기
        m.addAttribute("article", articleEntity.get());
        // 3. 뷰 페이지 반환하기
        return "articles/show";
    }

    @GetMapping("/articles")
    public String showAll(Model m) {
        // 1. DB에서 모든 데이터 가져오기
        List<Article> articleEntityList = articleRepository.findAll();
        // 2. model에 데이터 등록하기
        m.addAttribute("articleList", articleEntityList);
        // 3. 뷰 페이지 반환하기
        return "articles/index";
    }

    @GetMapping("/articles/{id}/edit")
    public String edit(@PathVariable Long id, Model m) {
        Article articleEntity = articleRepository.findById(id).orElse(null);
        m.addAttribute("article", articleEntity);
        return "articles/edit";
    }

    @PostMapping("/articles/update")
    public String edit(ArticleForm dto) {
        // 1. DTO를 Entity로 변환
        log.info(dto.toString());
        Article article = dto.toEntity();
        // 2. Entity를 DB에 저장
        // 2-1. DB에서 기존데이터 가져오기
        Article target = articleRepository.findById(article.getId()).orElse(null);
        // 2-2. 데이터 값 갱신하기
        if (target != null) {
            articleRepository.save(article);
        }
        // 3. 수정결과 페이지로 리다이렉트
        return "redirect:/articles/" + article.getId();
    }

    @GetMapping("/articles/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes rttr) {
        log.info("삭제 요청을 받았습니다.");
        // 1. 삭제할 대상 가져오기
        Article target = articleRepository.findById(id).orElse(null);
        // 2. 대상 entity 삭제하기
        if (target != null) {
            articleRepository.delete(target);
            rttr.addFlashAttribute("msg", "삭제되었습니다.");
        }
        // 3. 결과 페이지로 리다이렉트
        return "redirect:/articles";
    }
}
