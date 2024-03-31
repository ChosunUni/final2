package com.suntime.study.controller;

import com.suntime.study.dto.MemberDTO;
import com.suntime.study.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.Errors;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Map;

@Controller
public class MemberController {
    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/register")
    public String saveForm(Model model) {
        model.addAttribute("memberDTO", new MemberDTO()); // MemberDTO 객체를 모델에 추가
        return "register";
    }

    @PostMapping("/register")
    public String save(@Valid @ModelAttribute MemberDTO memberDTO, BindingResult result) {
        if (result.hasErrors()) {
            return "register"; // 유효성 검사 에러가 있으면 다시 폼으로 돌려보냄
        }
        // 회원가입 로직 수행
        memberService.save(memberDTO);
        return "index";
    }

    @PostMapping("/index")
    public String login(@ModelAttribute MemberDTO memberDTO, HttpServletRequest request, RedirectAttributes rttr) {
      MemberDTO loginResult = memberService.login(memberDTO);
      HttpSession session = request.getSession();
      MemberDTO login = memberService.login(memberDTO);
      session.setAttribute("loginMember", login);
      return "redirect:/timer";
    }

    @PostMapping("/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession();
        if (session != null) {
            session.invalidate(); // 세션 무효화
        }
//        sdsd
        return "redirect:/";
    }

}
