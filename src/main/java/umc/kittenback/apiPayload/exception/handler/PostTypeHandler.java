package umc.kittenback.apiPayload.exception.handler;

import javax.persistence.GeneratedValue;
import umc.kittenback.apiPayload.code.BaseErrorCode;
import umc.kittenback.apiPayload.exception.GeneralException;

public class PostTypeHandler extends GeneralException {

    public PostTypeHandler(BaseErrorCode code) {
        super(code);
    }
}
