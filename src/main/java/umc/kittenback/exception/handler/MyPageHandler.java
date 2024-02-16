package umc.kittenback.exception.handler;

import umc.kittenback.exception.GeneralException;
import umc.kittenback.response.code.BaseErrorCode;

public class MyPageHandler extends GeneralException {
    public MyPageHandler(BaseErrorCode errorCode) {
        super(errorCode);
    }
}
