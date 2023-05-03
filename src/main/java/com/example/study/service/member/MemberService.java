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

    public void handleLogin(final MemberLoginRequestDto memberLoginRequestDto) {
        // MemberLoginRequestDto의 값을 바탕으로 DB에서 Member를 찾고, 일치하는지 확인한다.
        Member member = memberRepository.findByEmail(memberLoginRequestDto.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("로그인 실패"));

        validateLogin(memberLoginRequestDto, member);
    }

    private static void validateLogin(final MemberLoginRequestDto memberLoginRequestDto, final Member member) {
        if (!member.isEmailCorrect(memberLoginRequestDto.getEmail())) {
            throw new IllegalArgumentException("멤버를 찾을 수 없습니다.");
        }

        if (!member.isPasswordCorrect(memberLoginRequestDto.getPassword())) {
            throw new IllegalArgumentException("패스워드 불일치");
        }
    }
}
