package com.shop.entity;

import com.shop.constant.Role;
import com.shop.dto.BoardWriteFormDto;
import com.shop.dto.MemberFormDto;
import com.shop.service.MemberService;
import jakarta.persistence.*;
import jakarta.servlet.http.HttpSession;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.security.Principal;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "board")
@Getter
@Setter
public class Board{
    @Id
    @Column(name = "board_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    private String content;
    private String writer;
    private LocalDate localDate;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;



    public static Board writeBoard(BoardWriteFormDto boardWriteFormDto, Member member, MemberService memberService,
                                   Principal principal, HttpSession httpSession){
        Board board=new Board();
        String name = memberService.loadMemberName(principal,httpSession);
        board.setTitle(boardWriteFormDto.getTitle());
        board.setContent(boardWriteFormDto.getContent());
        board.setLocalDate(LocalDate.now());
        board.setWriter(name);
        board.setMember(member);
        return board;
    }





}
