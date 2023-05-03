package com.example.study.util;

import com.example.study.dto.member.MemberLoginRequestDto;
import org.apache.tomcat.util.codec.binary.Base64;

public class BasicAuthorizationExtractor implements AuthorizationExtractor<MemberLoginRequestDto> {

    private static final String BASIC_TYPE = "Basic";
    private static final String DELIMITER = ":";

    @Override
    public MemberLoginRequestDto extractHeader(final String authorization) {
        validateAuthorization(authorization);
        return getMemberLoginRequestDto(authorization);
    }

    private MemberLoginRequestDto getMemberLoginRequestDto(final String authorization) {
        String authHeaderValue = authorization.substring(BASIC_TYPE.length()).trim();
        byte[] decodedBytes = Base64.decodeBase64(authHeaderValue);
        String decodedString = new String(decodedBytes);

        String[] credentials = decodedString.split(DELIMITER);
        String email = credentials[0];
        String password = credentials[1];

        return MemberLoginRequestDto.from(email, password);
    }

    private void validateAuthorization(final String authorization) {
        if (!IsBasicAuthorization(authorization) || authorization.isEmpty()) {
            throw new IllegalArgumentException("올바르지 않은 요청입니다.");
        }
    }

    private boolean IsBasicAuthorization(final String authorization) {
        return authorization.toLowerCase().startsWith(BASIC_TYPE.toLowerCase());
    }
}
