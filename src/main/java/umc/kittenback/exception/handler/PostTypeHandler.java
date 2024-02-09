package umc.kittenback.exception.handler;

import umc.kittenback.exception.GeneralException;
import umc.kittenback.response.code.BaseErrorCode;

public class PostTypeHandler extends GeneralException {

    public PostTypeHandler(BaseErrorCode code) {
        super(code);
    }
}
