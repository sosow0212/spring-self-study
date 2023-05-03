package com.example.study.service.member;

import com.example.study.dao.member.MemberRepository;
import com.example.study.domain.member.Member;
import com.example.study.dto.member.MemberLoginRequestDto;
import com.example.study.dto.member.MembersResponseDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(final MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Transactional(readOnly = true)
    public MembersResponseDto findAll() {
        List<Member> members = memberRepository.findAll();

        return MembersResponseDto.from(members);
    }
}
