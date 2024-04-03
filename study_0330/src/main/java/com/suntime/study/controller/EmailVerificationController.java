package com.suntime.study.controller;

import com.suntime.study.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/verify-email")
public class EmailVerificationController {

    @Autowired
    private MemberService memberService;

    public ResponseEntity<String> verifyEmail(@RequestParam("memberEmail") String memberEmail, @RequestParam("verificationToken") String token) {
        boolean verified = memberService.verifyEmail(memberEmail, token);
        if (verified) {
            return ResponseEntity.ok("Email verified successfully.");
        } else {
            return ResponseEntity.badRequest().body("Invalid verification link.");
        }
    }
}
