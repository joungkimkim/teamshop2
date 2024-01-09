package com.shop.entity;

import com.shop.config.Oauth2UserInfo;
import com.shop.config.PrincipalOauth2UserService;
import com.shop.constant.Role;
import com.shop.dto.MemberASDto;
import com.shop.dto.MemberFormDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "member")
@Getter
@Setter
@AllArgsConstructor
public class Member extends BaseEntity{
    @Id
    @Column(name = "member_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @Column(unique = true)
    private String email;

    private String password;


    private String address;
    private String provider;
    private String tel;
    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "member",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Board> boardList = new ArrayList<>();

    public Member() {
    }

    public static Member createMember(MemberFormDto memberFormDto,
                                      PasswordEncoder passwordEncoder){
        Member member = new Member();
        member.setName(memberFormDto.getName());
        member.setEmail(memberFormDto.getEmail());
        member.setAddress(memberFormDto.getAddress());
        member.setTel(memberFormDto.getTel());
        String password = passwordEncoder.encode(memberFormDto.getPassword());
        member.setPassword(password);
        member.setRole(Role.ADMIN);
        return member;
    }

    public static Member userMember(Oauth2UserInfo oauth2UserInfo){
        Member member = new Member();
        member.setName(oauth2UserInfo.getName());
        member.setEmail(oauth2UserInfo.getEmail());
        member.setProvider(oauth2UserInfo.getProvider());
        member.setRole(Role.USER);
        return member;
    }

    public Member( Role role, Oauth2UserInfo oAuth2UserInfo, PrincipalOauth2UserService principalOauth2UserService) {
        this.name = oAuth2UserInfo.getName();
        this.email = principalOauth2UserService.saveEmail(oAuth2UserInfo);
        this.role = role;
        this.provider = oAuth2UserInfo.getProvider();
    }

    public Member update(String name) {
        this.name = name;
        this.email = email;
        this.provider = provider;
        return this;
    }

    public Member MemberAS(MemberASDto memberASDto) {
        this.address = memberASDto.getAddress();
        this.tel = memberASDto.getTel();
        return this;
    }
}
