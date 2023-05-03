package com.example.study.config;

import com.example.study.dao.member.MemberRepository;
import com.example.study.domain.member.Member;
import com.example.study.dto.member.MemberLoginRequestDto;
import com.example.study.util.AuthorizationExtractor;
import com.example.study.util.BasicAuthorizationExtractor;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@Component
public class LoginMemberArgumentResolver implements HandlerMethodArgumentResolver {

    private static final String AUTHORIZATION_HEADER = "Authorization";

    private final AuthorizationExtractor<MemberLoginRequestDto> authorizationExtractor;
    private final MemberRepository memberRepository;

    public LoginMemberArgumentResolver(final MemberRepository memberRepository) {
        this.authorizationExtractor = new BasicAuthorizationExtractor();
        this.memberRepository = memberRepository;
    }

    @Override
    public boolean supportsParameter(final MethodParameter parameter) {
        return parameter.hasParameterAnnotation(LoginBasic.class);
    }

    @Override
    public Member resolveArgument(final MethodParameter parameter,
                                  final ModelAndViewContainer mavContainer,
                                  final NativeWebRequest webRequest,
                                  final WebDataBinderFactory binderFactory) throws Exception {

        MemberLoginRequestDto memberLoginRequestDto = getMemberLoginRequestDto(webRequest);
        return getMember(memberLoginRequestDto);
    }

    private MemberLoginRequestDto getMemberLoginRequestDto(final NativeWebRequest webRequest) {
        String authorization = webRequest.getHeader(AUTHORIZATION_HEADER);
        return authorizationExtractor.extractHeader(authorization);
    }

    public Member getMember(final MemberLoginRequestDto memberLoginRequestDto) {
        // MemberLoginRequestDto의 값을 바탕으로 DB에서 Member를 찾고, 일치하는지 확인한다.
        Member member = memberRepository.findByEmail(memberLoginRequestDto.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("로그인 실패"));

        validateLogin(memberLoginRequestDto, member);

        return member;
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
