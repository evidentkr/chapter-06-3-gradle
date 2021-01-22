package com.rubypaper.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.rubypaper.domain.Member;
import com.rubypaper.service.MemberService;

@SessionAttributes("member")
@Controller
public class LoginController {
    @Autowired
    private MemberService memberService;

    @GetMapping("/login")
    public void loginView() {
    }

    @PostMapping("/login")
    public String login(Member member, Model model) {
        Member findMember = memberService.getMember(member);

        if (findMember != null && findMember.getPassword().equals(member.getPassword())) {
            //세션에도 저장이 된다.  > @SessionAttributes("member")
            model.addAttribute("member", findMember);
            return "forward:getBoardList";
        } else {
            return "redirect:login";
        }
    }

    @GetMapping("/logout")
    public String logout(SessionStatus status) {
        // 로그아웃 처리
        status.setComplete();
        return "redirect:index.html";
    }

}
