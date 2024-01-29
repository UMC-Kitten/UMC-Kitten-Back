package umc.kittenback.apiPayload.code;

import umc.kittenback.apiPayload.code.ReasonDTO;

public interface BaseCode {

    public ReasonDTO getReason();

    public ReasonDTO getReasonHttpStatus();
}
