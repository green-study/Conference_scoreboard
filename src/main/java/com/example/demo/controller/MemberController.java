package com.example.demo.controller;
import com.example.demo.dto.MemberDto;
import com.example.demo.service.MemberService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.net.URISyntaxException;

@Controller
public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping("/join")
    public ResponseEntity<MemberDto> joinMember(@ModelAttribute MemberDto member) throws URISyntaxException {
        memberService.joinMember(member);
        return null;
    }
}