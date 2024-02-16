package umc.kittenback.exception.handler;

import umc.kittenback.exception.GeneralException;
import umc.kittenback.response.code.BaseErrorCode;

public class HealthNoteHandler extends GeneralException {
    public HealthNoteHandler(BaseErrorCode errorCode) { super(errorCode); }
}
