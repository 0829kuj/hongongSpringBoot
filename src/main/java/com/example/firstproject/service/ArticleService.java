package com.example.firstproject.service;

import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.entity.Article;
import com.example.firstproject.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class ArticleService {
    @Autowired
    private ArticleRepository articleRepository;

    public List<Article> index() {
        return articleRepository.findAll();
    }

    public Article show(Long id) {
        return articleRepository.findById(id).orElse(null);
    }

    public Article create(ArticleForm dto) {
        Article article = dto.toEntity();
        if (article.getId() != null){
            return null;
        }
        return articleRepository.save(article);
    }

    public Article update(Long id, ArticleForm dto) {
        // 1. DTO -> Entity 변환
        Article target = dto.toEntity();
        log.info("id: {}, article: {},", id, target.toString());
        // 2. 타깃 조회하기
        Optional<Article> article = articleRepository.findById(id);
        // 3. 잘못된 요청 처리하기
        if (article.isEmpty() || !id.equals(target.getId())) {
            // article객체가 비었거나 매개변수로 받아온 id와 article객체의 id가 동일하지 않을 경우
            log.info("잘못된 요청! id: {}, article: {}", id, article.toString());
            return null;
        }
        // 4. 업데이트 및 정상 응답(200)하기
        // 4-1. 클라이언트로부터 전달받지 못한 속성은 DB에서 조회해온 기존의 데이터로 대체
        article.get().patch(target);
        log.info("article id: {}, article: {}", id, article.toString());
        Article updated = articleRepository.save(article.get());
        return updated;
    }

    public Article delete(Long id) {
        // 1. DB에서 대상 Entity가 있는지 조회
        Optional<Article> target = articleRepository.findById(id);
        // 2. 대상 Entity가 없는 경우 처리
        if (target.isEmpty()) {
            return null;
        }
        // 3. Entity가 있을 경우 삭제 후 정상 응답(200) 반환
        articleRepository.delete(target.get());
        return target.get();
    }
}
