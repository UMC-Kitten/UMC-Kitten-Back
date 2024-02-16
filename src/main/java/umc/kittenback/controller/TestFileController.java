package umc.kittenback.controller;

import com.google.cloud.storage.Blob;
import java.io.IOException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import umc.kittenback.converter.ImageConverter;
import umc.kittenback.dto.image.ImageResponseDTO;
import umc.kittenback.response.ApiResponse;
import umc.kittenback.service.firebase.FireBaseService;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/api/v1/upload")
public class TestFileController {

    private final FireBaseService fireBaseService;

    @PostMapping("/{userId}/file")
    public String uploadFile(@RequestParam("file") MultipartFile file, Long userId) throws IOException {
        if (file.isEmpty()) {
            return "empty";
        }
        return fireBaseService.uploadFile(file, userId);
    }

    @PostMapping("/{userId}/files")
    public List<String> uploadFileList(@RequestParam("files") List<MultipartFile> files, Long userId)
            throws IOException {
        return fireBaseService.uploadFiles(files, userId);
    }

    @GetMapping("/{userId}/files")
    public ApiResponse<ImageResponseDTO.ImagePreviewListDTO> getFiles(@RequestParam Long userId) {
        List<Blob> files = fireBaseService.getFiles(userId);
        return ApiResponse.onSuccess(ImageConverter.toImagePreviewListDTO(files));
    }

//    @GetMapping("/{userId}/files")
//    public ResponseEntity<List<Blob>> getFiles(@PathVariable Long userId) {
//        List<Blob> fileList = fireBaseService.getFiles(userId);
//        if (fileList.isEmpty()) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//        return new ResponseEntity<>(fileList, HttpStatus.OK);
//    }
}
