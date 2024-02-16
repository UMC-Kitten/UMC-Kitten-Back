package umc.kittenback.exception.handler;

import umc.kittenback.exception.GeneralException;
import umc.kittenback.response.code.BaseErrorCode;

public class TokenHandler extends GeneralException {

    public TokenHandler(BaseErrorCode errorCode) {
        super(errorCode);
    }
}
