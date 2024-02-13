package umc.kittenback.exception.handler;

import umc.kittenback.exception.GeneralException;
import umc.kittenback.response.code.BaseErrorCode;

public class PetHandler extends GeneralException {

    public PetHandler(BaseErrorCode errorCode) {
        super(errorCode);
    }
}
