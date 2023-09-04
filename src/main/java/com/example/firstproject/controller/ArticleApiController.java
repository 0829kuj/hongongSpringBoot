package com.example.firstproject.controller;

import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.entity.Article;
import com.example.firstproject.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
public class ArticleApiController {
    @Autowired
    private ArticleRepository articleRepository;

    // GET
    @GetMapping("/api/articles")
    public List<Article> index() {
        return articleRepository.findAll();
    }

    @GetMapping("/api/articles/{id}")
    public Article show(@PathVariable Long id) {
        return articleRepository.findById(id).orElse(null);
    }

    // POST
    @PostMapping("/api/articles")
    public Article create(@RequestBody ArticleForm dto) {
        Article article = dto.toEntity();
        return articleRepository.save(article);
    }

    // PATCH
    @PatchMapping("/api/articles/{id}")
    public ResponseEntity<Article> update(@PathVariable Long id,
                                          @RequestBody ArticleForm dto) {
        // 1. DTO -> Entity 변환
        Article target = dto.toEntity();
        log.info("id: {}, article: {},", id, target.toString());
        // 2. 타깃 조회하기
        Optional<Article> article = articleRepository.findById(id);
        // 3. 잘못된 요청 처리하기
        if (article.isEmpty() || !id.equals(target.getId())) {
            // article객체가 비었거나 매개변수로 받아온 id와 article객체의 id가 동일하지 않을 경우
            log.info("잘못된 요청! id: {}, article: {}", id, article.toString());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        // 4. 업데이트 및 정상 응답(200)하기
        // 4-1. 클라이언트로부터 전달받지 못한 속성은 DB에서 조회해온 기존의 데이터로 대체
        target.patch(article.get());
        Article update = articleRepository.save(target);
        return ResponseEntity.status(HttpStatus.OK).body(update);
    }

    // DELETE
    @DeleteMapping("/api/articles/{id}")
    public ResponseEntity<Article> delete(@PathVariable Long id) {
        // 1. DB에서 대상 Entity가 있는지 조회
        Optional<Article> article = articleRepository.findById(id);
        // 2. 대상 Entity가 없는 경우 처리
        if (article.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        // 3. Entity가 있을 경우 삭제 후 정상 응답(200) 반환
        articleRepository.delete(article.get());
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
