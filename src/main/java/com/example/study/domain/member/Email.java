package com.example.study.domain.member;

import java.util.Objects;

public class Email {

    private static final String EMAIL_REGEX = "[0-9a-zA-Z]+(.[_a-z0-9-]+)*@(?:\\w+\\.)+\\w+$";

    private final String email;

    public Email(final String email) {
        validate(email);
        this.email = email;
    }

    private void validate(final String email) {
        if (email.isBlank()) {
            throw new IllegalArgumentException("이메일 양식 틀림");
        }

        if (!email.matches(EMAIL_REGEX)) {
            throw new IllegalArgumentException("패스워드 양식 틀림");
        }
    }

    public boolean isSameEmail(final String email) {
        return Objects.equals(this.email, email);
    }

    public String getEmail() {
        return email;
    }
}
