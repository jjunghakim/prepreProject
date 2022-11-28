package com.example.member.controller;

import com.example.member.dto.MemberDto;
import com.example.member.mapper.MemberMapper;
import com.example.member.service.MemberService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping("/v12/members")
@RestController
public class MemberController {
    private final MemberService memberService;
    private final MemberMapper mapper;

    public MemberController(MemberService memberService, MemberMapper mapper) {
        this.memberService = memberService;
        this.mapper = mapper;
    }
//DI


    @PostMapping
    public ResponseEntity postMember(@Valid @RequestBody MemberDto.Post memberDto){

        return new ResponseEntity(memberDto, HttpStatus.CREATED);
    }

    @PatchMapping("/{member-id}")
    public ResponseEntity patchMember(@Valid @PathVariable("member-id")
                                     @RequestBody MemberDto.Patch memberDto){
        return new ResponseEntity(memberDto, HttpStatus.OK);
    }

    @GetMapping("/{member-id}")
    public ResponseEntity getMembers(@PathVariable("member-id") long memberId){
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity getMember(){
        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping("/{member-id}")
    public ResponseEntity deleteMember(@PathVariable("member-id") long memberId){
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
