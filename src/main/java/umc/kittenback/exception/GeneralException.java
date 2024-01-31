package umc.kittenback.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import umc.kittenback.response.code.BaseErrorCode;
import umc.kittenback.response.code.ErrorReasonDTO;

@Getter
@AllArgsConstructor
public class GeneralException extends RuntimeException {

    private BaseErrorCode code;

    public ErrorReasonDTO getErrorReason() {
        return this.code.getReason();
    }

    public ErrorReasonDTO getErrorReasonHttpStatus() {
        return this.code.getReasonHttpStatus();
    }
}
