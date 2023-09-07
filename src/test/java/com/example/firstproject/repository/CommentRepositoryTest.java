package com.example.firstproject.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
class CommentRepositoryTest {
    @Autowired
    CommentRepository commentRepository;

    @Test
    @DisplayName("특정 게시글의 모든 댓글 조회")
    void findByArticleId(){
        // 1번 게시글의 모든 댓글 조회
//        {
           // 1. 입력 데이터 준비
            Long articleId = 1L;
           // 2. 실제 데이터
            int commentsCnt = commentRepository.findByArticleIdCnt(articleId);
           // 3. 예상 데이터
//            Article article = new Article(1L, "첫번쨰 게시글", "첫 게시글의 내용입니다.");
//            Comment a = new Comment(1L, article, "Park", "가나다라");
//            Comment b = new Comment(1L, article, "Kim", "마바사아");
//            Comment c = new Comment(1L, article, "Park", "자차카타");
//            Comment d = new Comment(1L, article, "Lee", "파하");
//            List<Comment> expected = Arrays.asList(a,b,c,d);
           // 4. 비교 및 검증
            System.out.println("commentsCnt = " + commentsCnt);
            int expected = 4;
            assertEquals(expected, commentsCnt, "1번 글의 모든 댓글을 출력!");
//        }
        {
//            // 1. 입력 데이터 준비
////            Long articleId = 1L;
//            // 2. 실제 데이터
//            List<Comment> comments = commentRepository.findAll();
//            // 3. 예상 데이터
////            Article article = new Article(1L, "첫번쨰 게시글", "첫 게시글의 내용입니다.");
////            Comment a = new Comment(1L, article, "Park", "가나다라");
////            Comment b = new Comment(1L, article, "Kim", "마바사아");
////            Comment c = new Comment(1L, article, "Park", "자차카타");
////            Comment d = new Comment(1L, article, "Lee", "파하");
////            List<Comment> expected = Arrays.asList(a,b,c,d);
//            // 4. 비교 및 검증
//            System.out.println("comments = " + comments);
//            System.out.println("commentsCnt = " + comments.size());
//            int expected = 4;
//            assertEquals(expected, comments.size(), "1번 글의 모든 댓글을 출력!");
        }

    }
//    @Test
//    @DisplayName("특정 닉네임의 모든 댓글 조회")
//    void findByNickname(){
//
//    }

}