package com.shop.controller;

import com.shop.dto.BoardWriteFormDto;
import com.shop.entity.Board;
import com.shop.entity.Member;
import com.shop.repository.BoardRepository;
import com.shop.service.BoardService;
import com.shop.service.MemberService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.time.LocalDate;
import java.util.Optional;

@Controller
@RequestMapping(value = "/faq")
@RequiredArgsConstructor
public class FaqController {
private final BoardRepository boardRepository;
private final BoardService boardService;
private final MemberService memberService;


    @GetMapping(value = "/boardList")
    public String boardList(Model model){
        String boardlist= boardService.getList().toString();

        System.out.println(boardlist + " 리스트");
        model.addAttribute("boardList",boardService.getList());

        return "/faq/board";
    }
    @GetMapping(value = "/writeForm")
    public String writeForm(Model model){

        model.addAttribute("writeFormDto",new BoardWriteFormDto());
        return "/faq/writeBoardForm";
    }
    @GetMapping(value = "/write")
    public String write(BoardWriteFormDto boardWriteFormDto, Member member,
                        Principal principal, HttpSession httpSession){
        System.out.println(boardWriteFormDto.getTitle() +" 제목 넘어오는지 확인");
        System.out.println(boardWriteFormDto.getContent() +" 내용 넘어오는지 확인");
        Board board = boardService.writeBoard(boardWriteFormDto,principal,httpSession);
        boardRepository.save(board);
        System.out.println("글작성완료");
        return "redirect:/";
    }


    @GetMapping(value = "/boardDetail/{id}")
    public String boardDetail(Model model, @PathVariable("id")Long boardId,Principal principal,HttpSession httpSession){
        String content = (String) httpSession.getAttribute("content");
        System.out.println(content + " boardid content");


        String name = memberService.loadMemberName(principal,httpSession);
        System.out.println(boardService.getId(boardId));
        // System.out.println(boardRepository.findById(boardId) + " dd");
        model.addAttribute("board",boardService.getId(boardId));

        return "/faq/boardDetail";
    }

    @PostMapping(value = "/update/{id}")
    public String update(@Param("title")String title, @Param("content")String content, @PathVariable("id") Long boardId, Model model){
        boardService.updateByBoardId(title,content,boardId);

        return "redirect:/";
    }

    @PostMapping(value = "/delete/{id}")
    public String delete(@PathVariable("id") Long boardId,Model model){
        boardService.deleteById(boardId);
        model.addAttribute("id",boardId);
        return "redirect:/";
    }
}
