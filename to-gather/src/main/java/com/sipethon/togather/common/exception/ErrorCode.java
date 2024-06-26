package com.sipethon.togather.common.exception;

import lombok.Getter;

@Getter
public enum ErrorCode {
    // Common
    INTERNAL_SERVER_ERROR(500, "C001", "서버 내부 에러입니다."),
    EXTERNAL_SERVER_ERROR(501, "C002", "외부 API 통신 에러입니다."),
    INVALID_INPUT_VALUE(400, "C003", "잘못된 요청 값입니다."),
    HANDLE_ACCESS_DENIED(403, "C004", "권한이 없는 사용자입니다."),

    // Member,
    DUPLICATE_USERNAME(400, "M001", "이미 등록된 username 입니다."),
    FAILED_SIGN_IN(400, "M002", "로그인에 실패하였습니다."),
    NOT_FOUND_MEMBER(400, "M003", "존재하지 않는 회원입니다."),

    // Content
    NOT_FOUND_CONTENT(400, "C001", "존재하지 않는 컨텐츠입니다."),
    ;

    private final int status;
    private final String code;
    private final String message;

    ErrorCode(int status, String code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }
}
