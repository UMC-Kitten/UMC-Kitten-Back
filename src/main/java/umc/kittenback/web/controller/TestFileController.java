package umc.kittenback.web.controller;

import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import umc.kittenback.service.firebase.FireBaseService;

@RestController
@RequiredArgsConstructor
@Validated
public class TestFileController {

    private final FireBaseService fireBaseService;

    @PostMapping("/files")
    public String uploadFile(@RequestParam("file")MultipartFile file, String fileName) throws IOException {
        if(file.isEmpty()){
            return "empty";
        }
        return fireBaseService.uploadFiles(file, fileName);
    }
}
