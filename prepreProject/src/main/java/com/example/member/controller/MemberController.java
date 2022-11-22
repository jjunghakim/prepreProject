package com.example.member.controller;

import com.example.member.dto.MemberDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/v12/members")
@RestController
public class MemberController {

    @PostMapping
    public ResponseEntity postMember(MemberDto.Post memberDto){
        return new ResponseEntity(memberDto, HttpStatus.CREATED);
    }

    @PatchMapping("/{member-id}")
    public ResponseEntity patchMember(@PathVariable("member-id")
                                      MemberDto.Patch memberDto){
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
