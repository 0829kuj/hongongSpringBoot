package com.example.firstproject.dto;

import com.example.firstproject.entity.Article;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.ToString;

// dto 클래스
//@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class ArticleForm {
    private Long id;
    private String title;
    private String content;


//    이 부분 생성자말고 빌더패턴으로 변경하고싶은데 잘안돼서 그냥놔둠
    public Article toEntity(){
        return new Article(id, title, content);
    }
//    public static Article create(ArticleForm dto){
//    return Article.Builder()
//            .title(dto.getTitle())
//            .content(dto.getContent())
//            .build();
//}

//    public static User create(UserDto dto){
//        return User.builder()
//                .user_id(dto.getId())
//                .pwd(dto.getPwd())
//                .name(dto.getName())
//                .gender(dto.getGender())
//                .useYN("Y")
//                .birthday(dto.getBirthday())
//                .build();
//    }
}
