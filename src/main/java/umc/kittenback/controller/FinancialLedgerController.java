package umc.kittenback.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import java.util.Date;
import java.util.List;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import umc.kittenback.converter.FinancialLedgerConverter;
import umc.kittenback.domain.FinancialLedger;
import umc.kittenback.dto.FinancialLedger.FinancialLedgerResponseDTO.JoinFinancialLedgerResultDTO;
import umc.kittenback.response.ApiResponse;
import umc.kittenback.service.FinancialLedger.FinancialLederQueryServiceImpl;
import umc.kittenback.service.FinancialLedger.FinancialLedgerCommandServiceImpl;
import umc.kittenback.dto.FinancialLedger.FinancialLedgerRequestDTO;
import umc.kittenback.dto.FinancialLedger.FinancialLedgerResponseDTO;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/api/v1/financialLedger")
public class FinancialLedgerController {

    private final FinancialLedgerCommandServiceImpl financialLedgerCommandService;
    private final FinancialLederQueryServiceImpl financialLederQueryService;

    // 가계부 등록
    @PostMapping("/{userId}")
    @Operation(summary = "가계뿌 등록 API",description = "가계부을 등록하는 API입니다.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200",description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH003", description = "access 토큰을 주세요!",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH004", description = "acess 토큰 만료",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH006", description = "acess 토큰 모양이 이상함",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
    })
    @Parameters({
            @Parameter(name = "userId", description = "사용자 고유번호, path variable 입니다!")
    })
    public ApiResponse<JoinFinancialLedgerResultDTO> joinFinancialLedger(@PathVariable(name = "userId") Long userId, @RequestBody @Valid FinancialLedgerRequestDTO.JoinFinancialLedgerDTO req){
        FinancialLedger financialLedger = financialLedgerCommandService.joinFinancialLedger(userId, req);
        return ApiResponse.onSuccess(FinancialLedgerConverter.toJoinFinancialLedgerResultDTO(financialLedger));
    }

    // 가계부 수정
    @PutMapping("/{financialLedgerId}")
    @Operation(summary = "가계부 수정 API",description = "가계부을 수정하는 API입니다.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200",description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH003", description = "access 토큰을 주세요!",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH004", description = "acess 토큰 만료",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH006", description = "acess 토큰 모양이 이상함",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
    })
    @Parameters({
            @Parameter(name = "financialLedgerId", description = "가계부 고유번호, path variable 입니다!")
    })
    public ApiResponse<FinancialLedgerResponseDTO.JoinFinancialLedgerResultDTO> updateFinancialLedger(@PathVariable(name = "financialLedgerId") Long financialLedgerId, @RequestBody @Valid FinancialLedgerRequestDTO.JoinFinancialLedgerDTO req){
        FinancialLedger financialLedger = financialLedgerCommandService.updateFinancialLedger(financialLedgerId, req);
        return ApiResponse.onSuccess(FinancialLedgerConverter.toJoinFinancialLedgerResultDTO(financialLedger));
    }

    // 가계부 삭제
    @DeleteMapping("/{financialLedgerId}")
    @Operation(summary = "가계부 삭제 API")
    public ResponseEntity<Boolean> deleteFinancialLedger(@PathVariable Long financialLedgerId){
        return ResponseEntity.ok(financialLedgerCommandService.deleteFinancialLedger(financialLedgerId));
    }

    // 가계부 불러오기
    @GetMapping("/{userId}/{day}")
    @Operation(summary = "가계부 조회 API",description = "가계부을 조회하는 API입니다.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200",description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH003", description = "access 토큰을 주세요!",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH004", description = "acess 토큰 만료",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH006", description = "acess 토큰 모양이 이상함",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
    })
    @Parameters({
            @Parameter(name = "userId", description = "사용자 고유번호, path variable 입니다!")
    })
    public ApiResponse<FinancialLedgerResponseDTO.FinancialLedgerPreviewListDTO> getFinancialLedger(@PathVariable(name = "userId") Long userId, @PathVariable(name = "day") Date day){
        List<FinancialLedger> financialLedgers = financialLederQueryService.getFinancialLedger(userId, day);
        return ApiResponse.onSuccess(FinancialLedgerConverter.toFinancialLedgerPreviewListDTO(financialLedgers));
    }


}
