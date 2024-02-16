package umc.kittenback.controller;

import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import umc.kittenback.dto.checkIn.HealthNote.HealthNotePetsResponseDto;
import umc.kittenback.dto.checkIn.HealthNote.HealthNoteRequestDto.EditHealthNoteDto;
import umc.kittenback.dto.checkIn.HealthNote.HealthNoteRequestDto.WriteHealthNoteDto;
import umc.kittenback.dto.hospital.HospitalResponseDto;
import umc.kittenback.response.ApiResponse;
import umc.kittenback.service.healthNote.HealthNoteCommandServiceImpl;
import umc.kittenback.service.healthNote.HealthNoteQueryServiceImpl;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/healthNote")
public class HealthNoteController {
    private final HealthNoteQueryServiceImpl HealthNoteQueryService;
    private final HealthNoteCommandServiceImpl HealthNoteCommandService;

    @GetMapping("/Pets/{id}")
    @Operation(summary = "건강수첩 접속 API", description = "건강수첩 접속 시 키우고 있는 애완동물 출력해주는 API")
    // 건강수첩 접속 시 건강수첩을 보기 위해 선택해야할 애완동물들을 보여주는 API
    // Jwt에서 id값 추출 가능할 경우 Mapping 변경될 예정
    public ApiResponse<HealthNotePetsResponseDto> getHealthNotePetsInfo(@PathVariable Long id) {
        HealthNotePetsResponseDto PetsResponseDto = HealthNoteQueryService.getHealthNotePetsInfo(id);
        return ApiResponse.onSuccess(PetsResponseDto);
    }

    @PostMapping("/write/{id}")
    @Operation(summary = "건강수첩 작성 API", description = "건강수첩 작성 시 사용되는 API.")
    // 건강수첩 작성 시 사용되는 API
    // Jwt에서 id값 추출 가능할 경우 Mapping 변경될 예정
    public ApiResponse<Boolean> writeHealthNote(@PathVariable Long id,
                                                @RequestBody WriteHealthNoteDto req) {
        return ApiResponse.onSuccess(HealthNoteCommandService.writeHealthNote(id, req));
    }

    @PutMapping("/{userId}")
    @Operation(summary = "건강수첩 수정 API", description = "건강수첩 수정 시 사용되는 API.")
    // Jwt에서 id값 추출 가능할 경우 Mapping 변경될 예정
    public ApiResponse<Boolean> editHealthNote(@PathVariable Long userId,
                                               @RequestParam Long id,
                                               @RequestBody EditHealthNoteDto req) {
        return ApiResponse.onSuccess(HealthNoteCommandService.editHealthNote(userId, id, req));
    }

    @DeleteMapping("/{userId}")
    @Operation(summary = "건강수첩 삭제 API", description = "건강수첩 삭제 시 사용되는 API.")
    // Jwt에서 id값 추출 가능할 경우 Mapping 변경될 예정
    public ApiResponse<Boolean> deleteHealthNote(@PathVariable Long userId,
                                                 @RequestParam Long id) {
        return ApiResponse.onSuccess(HealthNoteCommandService.deleteHealthNote(userId, id));
    }

    @GetMapping("/hospital")
    @Operation(summary = "건강수첩 병원 검색 API", description = "건강수첩 작성 중 병원 검색에 사용되는 API.")
    public ApiResponse<HospitalResponseDto> searchHospital(@RequestParam String keyword) {
        HospitalResponseDto hospitalResponseDto = HealthNoteQueryService.getHospitalSearchInfo(keyword);
        return ApiResponse.onSuccess(hospitalResponseDto);
    }

}
