package umc.kittenback.exception.handler;

import umc.kittenback.exception.GeneralException;
import umc.kittenback.response.code.BaseErrorCode;

public class UserHandler extends GeneralException {

    public UserHandler(BaseErrorCode errorCode) {
        super(errorCode);
    }
}
