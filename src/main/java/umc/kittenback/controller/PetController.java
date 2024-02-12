package umc.kittenback.controller;

import io.swagger.models.Response;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import umc.kittenback.domain.User;
import umc.kittenback.dto.pet.PetRequestDto;
import umc.kittenback.dto.pet.PetResponseDto;
import umc.kittenback.exception.handler.UserHandler;
import umc.kittenback.repository.UserRepository;
import umc.kittenback.response.ApiResponse;
import umc.kittenback.response.code.status.ErrorStatus;
import umc.kittenback.service.pet.PetServiceImpl;
import umc.kittenback.service.user.UserServiceImpl;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class PetController {

    private final UserRepository userRepository;
    private final UserServiceImpl userService;
    private final PetServiceImpl petService;

    @PostMapping("/{userId}/pet")
    @Operation(summary = "반려동물 등록 API",description = "반려동물을 등록하는 API입니다.")
    @Parameters({
            @Parameter(name = "userId", description = "사용자 고유번호, path variable 입니다!")

    })
    public ResponseEntity<ApiResponse<List<PetResponseDto>>> registerPet(@PathVariable(name = "userId") Long userId, @RequestBody List<PetRequestDto> petRegisterRequest) {
        User user = userRepository.findById(userId).orElseThrow(() ->
                new UserHandler(ErrorStatus.USER_NOT_FOUND));

        List<PetResponseDto> petResponseDtos = petService.savePets(user, petRegisterRequest);

        return ResponseEntity.ok()
                .body(ApiResponse.onSuccess(petResponseDtos));
    }

    @DeleteMapping("/pet/{petId}")
    @Operation(summary = "반려동물 삭제 API", description = "등록된 반려동물을 삭제합니다.")
    @Parameters({
            @Parameter(name = "petId", description = "pet 고유번호, path variable 입니다!")

    })
    public ResponseEntity<ApiResponse<Void>> deletePet(@PathVariable(name = "petId") Long petId) {
        petService.deletePet(petId);
        return ResponseEntity.ok()
                .body(ApiResponse.onSuccess(null));
    }

    @GetMapping("/{userId}/pets")
    @Operation(summary = "반려동물 조회 API", description = "사용자의 등록된 반려동물을 모두 조회합니다.")
    @Parameters({
            @Parameter(name = "userId", description = "사용자 고유번호, path variable 입니다!")

    })
    public ResponseEntity<ApiResponse<List<PetResponseDto>>> getAllPets(@PathVariable(name = "userId") Long userId) {
        List<PetResponseDto> allPetsByUserId = petService.findAllPetsByUserId(userId);
        return ResponseEntity.ok()
                .body(ApiResponse.onSuccess(allPetsByUserId));
    }


}
