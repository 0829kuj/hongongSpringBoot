package com.example.firstproject.entity;

import com.example.firstproject.dto.CommentDto;
import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Entity
@Builder
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;            // 기본키

    @ManyToOne
    @JoinColumn(name = "article_id")    // 외래키 생성, Article Entity의 기본키(id)와 매핑
    private Article article;    // 해당 댓글의 게시글

    @Column
    private String nickname;    // 댓글 작성자
    @Column
    private String body;        // 댓글 본문

    public static Comment createComment(CommentDto dto, Article article) {
        System.out.println("dto.toString() = " + dto.toString());
        // 예외 발생
        if (dto.getId() != null) {
            throw new IllegalArgumentException("댓글 생성 실패! 댓글의 id가 없어야 합니다.");
        }
        if (!Objects.equals(dto.getArticle_id(), article.getId())) {
            System.out.println("dto.getArticle_id() = " + dto.getArticle_id() + " article.getId() = " + article.getId());
            throw new IllegalArgumentException("댓글 생성 실패! 게시글의 id가 잘못되었습니다.");
        }
        // Entity생성 및 반환
        return new Comment(
                dto.getId(),
                article,
                dto.getNickname(),
                dto.getBody()
        );
    }

    public void patch(CommentDto dto) {
        // 예외 발생
        if (!Objects.equals(this.id, dto.getId())) {
            throw new IllegalArgumentException("댓글 수정 실패! 잘못된 id가 입력되었습니다.");
        }
        // 객체 갱신
        if (dto.getNickname() != null) {
            this.nickname = dto.getNickname();
        }
        if (dto.getBody() != null) {
            this.body = dto.getBody();
        }
    }
}
