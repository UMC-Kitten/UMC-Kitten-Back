package umc.kittenback.controller;

import java.io.IOException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import umc.kittenback.service.firebase.FireBaseService;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/upload")
public class TestFileController {

    private final FireBaseService fireBaseService;

    @PostMapping("/{userId}/file")
    public String uploadFile(@RequestParam("file")MultipartFile file, Long userId) throws IOException {
        if(file.isEmpty()){
            return "empty";
        }
        return fireBaseService.uploadFile(file, userId);
    }

    @PostMapping("/{userId}/files")
    public  List<String>  uploadFileList(@RequestParam("files")List<MultipartFile> files, Long userId) throws IOException {
        return fireBaseService.uploadFiles(files, userId) ;
    }
}
