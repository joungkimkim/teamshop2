package com.shop.controller;

import com.shop.dto.BoardWriteFormDto;
import com.shop.dto.ItemSearchDto;
import com.shop.dto.MainItemDto;
import com.shop.entity.Board;
import com.shop.entity.Item;
import com.shop.entity.Member;
import com.shop.repository.BoardRepository;
import com.shop.repository.MemberRepository;
import com.shop.service.BoardService;
import com.shop.service.ItemService;
import com.shop.service.MemberService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.data.web.PageableDefault;
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
private final ItemService itemService;
private final MemberRepository memberRepository;

    @GetMapping(value = "/boardLists")
    public String boardList(Model model, @PageableDefault Pageable page, ItemSearchDto itemSearchDto, Principal principal,
                            HttpSession httpSession){
        Page<Board> boardList = boardService.getBoardList(page);
        System.out.println(boardList + " 여기테스트");
        model.addAttribute("boardList", boardList);
        model.addAttribute("boardLists", boardList.getTotalElements());
        String name = memberService.loadMemberName(principal,httpSession);
        model.addAttribute("name",name);
        model.addAttribute("maxPage",5);
       // model.addAttribute("boardList",boardService.getList());
        return "/faq/board";
    }
    @GetMapping(value = "/writeForm")
    public String writeForm(Model model){
        model.addAttribute("writeFormDto",new BoardWriteFormDto());
        return "/faq/writeBoardForm";
    }
    @GetMapping(value = "/write")
    public String write(BoardWriteFormDto boardWriteFormDto, Principal principal, HttpSession httpSession){
        System.out.println(boardWriteFormDto.getTitle() +" 제목 넘어오는지 확인");
        System.out.println(boardWriteFormDto.getContent() +" 내용 넘어오는지 확인");
        Board board = boardService.writeBoard(boardWriteFormDto,principal,httpSession);
        boardRepository.save(board);
        System.out.println("글작성완료");
        return "redirect:/";
    }


    @GetMapping(value = "/boardDetail/{id}")
    public String boardDetail(Model model, @PathVariable("id")Long boardId,Principal principal,HttpSession httpSession){
        String name = memberService.loadMemberName(principal,httpSession);
        System.out.println(boardService.getId(boardId));
        model.addAttribute("board",boardService.getId(boardId));
        model.addAttribute("name",name);
        return "/faq/boardDetail";
    }
    @GetMapping(value = "/userDetail/{id}")
    public String userBoardDetail(Model model, @PathVariable("id")Long boardId,Principal principal,HttpSession httpSession){
        String name = memberService.loadMemberName(principal,httpSession);
        System.out.println(boardService.getId(boardId));

        model.addAttribute("board",boardService.getId(boardId));
        model.addAttribute("name",name);
        return "/faq/userBoardDetail";
    }

    @RequestMapping(value = "/detail/{id}")
    public String detail(Model model, @PathVariable("id")Long boardId,Principal principal,HttpSession httpSession){
        String name = memberService.loadMemberName(principal,httpSession);
        System.out.println(boardService.getId(boardId));
        String email = memberService.loadMemberEmail(principal,httpSession);
        Member member =memberRepository.findByEmail(email);
        if (member == null){
            System.out.println("로그인 후 이용해주세요");
            return "redirect:/members/login";
        }
        String role= String.valueOf(member.getRole());
        System.out.println(role + " 테스트 롤");
        if (role.equals("ADMIN")){
            model.addAttribute("board",boardService.getId(boardId));
            model.addAttribute("name",name);
            return "redirect:/faq/boardDetail/{id}";
        }
        if (role.equals("USER")) {
            model.addAttribute("board", boardService.getId(boardId));
            model.addAttribute("name", name);
            return "redirect:/faq/userDetail/{id}";
        }
        return "redirect:/faq/userDetail/{id}";
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
