package com.example.firstproject.controller;

import com.example.firstproject.dto.MemberForm;
import com.example.firstproject.entity.Member;
import com.example.firstproject.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Slf4j
@Controller
public class MemberController {

    @Autowired
    MemberRepository memberRepository;

    @GetMapping("/signup")
    public String signUpPage() {
        return "members/signup";
    }

    @PostMapping("/join")
    public String join(MemberForm form) {
        Member member = form.toEntity();
        log.info(form.toString());

        Member saved = memberRepository.save(member);
        log.info(saved.toString());
        log.info("회원가입이 완료되었습니다. 로그인 해주세요.");
        return "members/login";
    }

    @GetMapping("/login")
    public String logInPage() {
        return "members/login";
    }

    @GetMapping("/members/{id}")
    public String findId(@PathVariable Long id, Model m) {
        Optional<Member> memberEntity = memberRepository.findById(id);
        m.addAttribute("member", memberEntity.get());
        return "members/show";
    }

    @GetMapping("/members")
    public String findAllId(Model m) {
        List<Member> memberEntityList = (List<Member>) memberRepository.findAll();
//        List<Member> memberEntityList = memberRepository.findAll();
        m.addAttribute("memberList", memberEntityList);
        return "members/index";
    }

    @PostMapping("/login")
    public String logIn(@RequestBody MemberForm form){
        log.info(form.toString());
        //
        Optional<Member> member = memberRepository.findById(form.getId());
        if (member.get().getPassword().equals(form.getPassword())){
            return "redirect:/login";
        }
        return "articles/index";
    }
}
