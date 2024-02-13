package umc.kittenback.response.code.status;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import umc.kittenback.response.code.BaseErrorCode;
import umc.kittenback.response.code.ErrorReasonDTO;

@Getter
@AllArgsConstructor
public enum ErrorStatus implements BaseErrorCode {

    // 일반적인 응답
    _INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "COMMON500", "서버 에러, 관리자에게 문의 바랍니다."),
    _BAD_REQUEST(HttpStatus.BAD_REQUEST,"COMMON400","잘못된 요청입니다."),
    _UNAUTHORIZED(HttpStatus.UNAUTHORIZED,"COMMON401","인증이 필요합니다."),
    _FORBIDDEN(HttpStatus.FORBIDDEN, "COMMON403", "금지된 요청입니다."),

    // User 관련 에러
    USER_NOT_FOUND(HttpStatus.BAD_REQUEST, "USER4001", "사용자가 없습니다."),
    NICKNAME_INPUT_NOT_EXIST(HttpStatus.BAD_REQUEST, "USER4002", "닉네임을 입력해주세요"),
    DUPLICATE_NICKNAME(HttpStatus.BAD_REQUEST, "USER4003", "이미 사용중인 닉네임입니다."),

    FAIL_PARSE_APPLE_IDENTITY_TOKEN(HttpStatus.BAD_REQUEST, "TOKEN4001", "Apple Identity Token 헤더 파싱을 실패하였습니다."),
    FAIL_PARSE_CLAIM(HttpStatus.BAD_REQUEST, "TOKEN4002", "Claim을 파싱하는데 실패하였습니다."),
    INVALID_APPLE_OAUTH_CLAIMS(HttpStatus.BAD_REQUEST, "TOKEN4003", "유효하지 않은 Apple OAuth Claim입니다."),

    // PostType Error
    POSTTYPE_NOT_FOUND(HttpStatus.NOT_FOUND, "POSTTYPE4001", "해당하는 게시글타입이 없습니다."),

    // Pet Error
    PET_NOT_FOUND(HttpStatus.NOT_FOUND, "PET4001", "해당하는 반려동물이 없습니다.");

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;

    @Override
    public ErrorReasonDTO getReason() {
        return ErrorReasonDTO.builder()
                .message(message)
                .code(code)
                .isSuccess(false)
                .build();
    }

    @Override
    public ErrorReasonDTO getReasonHttpStatus() {
        return ErrorReasonDTO.builder()
                .message(message)
                .code(code)
                .isSuccess(false)
                .httpStatus(httpStatus)
                .build();
    }

}
