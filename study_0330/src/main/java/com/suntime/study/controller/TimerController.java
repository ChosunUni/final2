package com.suntime.study.controller;
import com.suntime.study.dto.MemberDTO;
import com.suntime.study.entity.TimerEntity;
import com.suntime.study.dto.TimerDTO;
import com.suntime.study.service.TimerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class TimerController {
    private final TimerService timerService;

    @PostMapping("/timer/subject")
    public String subject(@ModelAttribute TimerDTO timerDTO){
        // POST ��û���� ������ �޾Ƽ� ���񽺸� ���� �����մϴ�.
        System.err.println("timerDTO = " + timerDTO);
        timerService.save(timerDTO);
        return "redirect:/timer";
    }

    @GetMapping("/timer")
    public String subAllList(@SessionAttribute(name = "loginMember", required = false) MemberDTO loginMember, Model model) {
        List<TimerEntity> list = timerService.subAll();
        model.addAttribute("loginMember", loginMember);
        model.addAttribute("list", list);
        return "timer";
    }

    @PostMapping("/timer/del/{id}")
    public String delDataById(@PathVariable Long id){
        timerService.delDataById(id);
        return "redirect:/timer"; // timer �������� �����̷�Ʈ
    }
}