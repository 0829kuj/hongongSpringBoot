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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    public String join(MemberForm dto) {
        Member member = dto.toEntity();
        log.info(dto.toString());

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
        m.addAttribute("memberList", memberEntityList);
        return "members/index";
    }

    @PostMapping("/login")
    public String logIn(@RequestBody MemberForm dto) {
        log.info(dto.toString());
        Optional<Member> member = memberRepository.findById(dto.getId());
        if (member.get().getPassword().equals(dto.getPassword())) {
            return "redirect:/login";
        }
        return "articles/index";
    }

    @GetMapping("/members/{id}/edit")
    public String edit(@PathVariable Long id, Model m) {
        Member member = memberRepository.findById(id).orElse(null);
        m.addAttribute("member", member);
        return "/members/edit";
    }

    @PostMapping("/members/update")
    public String edit(MemberForm dto) {
        Member member = dto.toEntity();
        log.info("member = " + member.toString());
        // DB에서 기존데이터 검색
        Member target = memberRepository.findById(member.getId()).orElse(null);
        // 데이터 수정
        if (target != null) {
            memberRepository.save(member);
        }
        // 수정된 페이지로 리다이렉트
        return "redirect:/members/" + member.getId();
    }

    @GetMapping("/members/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes rttr) {
        // 1. DB에서 객체 가져오기
        Member member = memberRepository.findById(id).orElse(null);
        // 2. 삭제
        if (member != null) {
            memberRepository.delete(member);
            rttr.addFlashAttribute("msg", "삭제완료되었습니다.");
        }
        // 3. 리다이렉트
        return "redirect:/members";
    }
}
