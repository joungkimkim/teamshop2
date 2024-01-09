package com.shop.controller;

import com.shop.dto.MemberASDto;
import com.shop.service.MemberService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
@RequestMapping("/profile")
public class ProfileController {
    private final MemberService memberService;
    private final HttpSession httpSession;

    /*
        new

        memberProfile.html      회원정보 수정
        ProfileController.java  컨트롤러
        MemberASDto.java        DTO

        as

        header.html
        memberForm.html         회원가입
        MemberService.java      서비스
        Member.java             엔티티

        map 없음
    */
    @GetMapping(value = "/myInfoAS")
    public String infoAS(Principal principal, Model model) {
        MemberASDto memberASDto = MemberASDto.of(memberService.findMember(httpSession, principal));
        String name = memberService.loadMemberName(principal,httpSession);
        model.addAttribute("name",name);
        model.addAttribute("memberASDto", memberASDto);
        return "/member/memberProfile";
    }

    @PostMapping(value = "/myInfoAS")
    public String infoUpdate(@Valid MemberASDto memberASDto, BindingResult bindingResult,
                             Principal principal, Model model) {
        String name = memberService.loadMemberName(principal,httpSession);
        model.addAttribute("name",name);
        if (bindingResult.hasErrors()) {
            return "/member/memberProfile";
        }
        memberService.memberAS(memberASDto);
        return "redirect:/";
    }
}
