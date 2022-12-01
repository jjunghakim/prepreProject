package com.example.member.service;

import com.example.exception.BusinessLogicException;
import com.example.exception.ExceptionCode;
import com.example.member.entity.Member;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {

    public Member createMember(Member member){
     return null;
    }

    public Member updateMember(Member member){
        return null;
    }

    public Member findMember(long memberId){

        throw new BusinessLogicException(ExceptionCode.MEMBER_NOTFOUND);
    }

    public List<Member> findMembers(){
        return null;
    }

    public Member deleteMember(long memberId){
        return null;
    }
}
