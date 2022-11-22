package com.example.member.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

public class MemberDto {
    @Getter
    @AllArgsConstructor
    public static class Post{
        private String email;
        private String name;
        private String phone;
    }

    @Getter
    @AllArgsConstructor
    public static class Patch{
        private long memberId;
        private String name;
        private String phone;
//        private Member.MemberStatus memberStatus
    }
}
