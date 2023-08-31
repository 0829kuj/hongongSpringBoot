package com.example.firstproject.dto;

import com.example.firstproject.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
public class MemberForm {
    private Long id;
    private String email;
    private String password;
    private String name;

    public Member toEntity(){
        return new Member(email, password, name);
    }
}
