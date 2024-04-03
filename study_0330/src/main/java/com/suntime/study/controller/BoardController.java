package com.suntime.study.controller;

import com.suntime.study.dto.BoardDTO;
import com.suntime.study.entity.Board;
import com.suntime.study.repository.BoardRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;

@Slf4j
@Controller
public class BoardController {
    @Autowired // 스프링 부트가 미리 생성해 놓은 리파지터리 객체 주입(DI)
    private BoardRepository boardRepository;

    @GetMapping("/board/write")
    public String newBoardForm(){
        return "board/write";
    }
    @PostMapping("/board/create")
    public String createBoard(BoardDTO boardDTO){
        log.info(boardDTO.toString());
//        System.out.println(form.toString());
        // 1. DTO를 엔티티로 변환
        Board board = boardDTO.toEntity();
        log.info(board.toString());
//        System.out.println(board.toString());
        // 2. 리파지터리로 엔티티를 DB에 저장
        Board saved = boardRepository.save(board);
        log.info(saved.toString());
//        System.out.println(saved.toString());
        return "redirect:/board/" + saved.getId(); // 리다이렉트를 작성할 위치
    }
    @GetMapping("/board/{id}") // 데이터 조회 요청 접수
    public String show(@PathVariable Long id, Model model){ // 매개변수로 id 받아 오기
        log.info("id = " + id);
        // 1. id를 조회해 데이터 가져오기
        Board boardEntity = boardRepository.findById(id).orElse(null); // 이는 id 값으로 데이터를 찾을 때 해당 id 값이 없으면 null을 반환하라는 뜻입니다.
        // 2. 모델에 데이터 등록하기
        model.addAttribute("board",boardEntity); // 변수값을 "변수명"이라는 이름으로 추가
        // 3. 뷰 페이지 반환하기
        return "board/show";
    }
    @GetMapping("/board")
    public String index(Model model){ // model 객체 받아오기
        // 1. 모든 데이터 가져오기
        ArrayList<Board> boardEntityList = boardRepository.findAll();
        // 2. 모델에 데이터 등록하기
        model.addAttribute("boardList",boardEntityList);
        // 3. 뷰 페이지 설정하기
        return "board/index";
    }

}
