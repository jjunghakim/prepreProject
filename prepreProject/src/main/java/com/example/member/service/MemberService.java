package com.example.member.service;

import com.example.exception.BusinessLogicException;
import com.example.exception.ExceptionCode;
import com.example.member.entity.Member;
import com.example.member.repository.MemberRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class MemberService {
    private MemberRepository memberRepository;

    //Repository DI
    private MemberService(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }

    //멤버 생성
    // (리포지터리에 저장하는 기본적 crud 코드들은 JpaRepository 를 extend 해서 안적어준것)

    public Member createMember(Member member){
        verifyExistsEmail(member.getEmail());

        return memberRepository.save(member);
    }

    public Member updateMember(Member member){
        Member findMember = findVerifiedMember(member.getMemberId());

        Optional.ofNullable(member.getName())
                .ifPresent(name -> findMember.setName(name));
        Optional.ofNullable(member.getPhone())
                .ifPresent(phone -> findMember.setPhone(phone));
        Optional.ofNullable(member.getMemberStatus())
                .ifPresent(memberStatus -> findMember.setMemberStatus(memberStatus));

        //update 했을 경우 수정 시간이 들어가므로 setModifiedAt 해줌
        findMember.setModifiedAt(LocalDateTime.now());
        return memberRepository.save(findMember);
    }

    public Member findMember(long memberId){
        return findVerifiedMember(memberId);
    }

    public List<Member> findMembers(){
        return (List<Member>) memberRepository.findAll();
    }

    public void deleteMember(long memberId){
        Member findMember = findVerifiedMember(memberId);
        memberRepository.delete(findMember);
    }

    //예외 발생의 목적을 잘 따질 것
    //등록된 회원 찾기 메서드 (post != find)
    public Member findVerifiedMember(long memberId){
        Optional<Member> optionalMember =
                memberRepository.findById(memberId);
        Member findMember =
                optionalMember.orElseThrow(() ->
                        new BusinessLogicException(ExceptionCode.MEMBER_NOTFOUND));
        return findMember;
    }

    // ↕ : findVerifiedMember는 멤버가 없을시 MEMBER_NOTFOUND
    // verifyExistsEmail 는 해당하는 멤버가 이미 있는 경우 MEMBER_EXISTS

    //이메일 검증 메서드
    //이미 등록된 이메일이라면 BusinessLogic Exception
    public void verifyExistsEmail(String email){
        Optional<Member> member = memberRepository.findByEmail(email);
        if(member.isPresent())
            throw new BusinessLogicException(ExceptionCode.MEMBER_EXISTS);
    }
}
