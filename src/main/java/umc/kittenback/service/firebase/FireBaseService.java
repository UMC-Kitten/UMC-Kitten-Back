package umc.kittenback.service.firebase;

import com.google.cloud.storage.Blob;
import com.google.cloud.storage.Bucket;
import com.google.firebase.cloud.StorageClient;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
@Transactional
public class FireBaseService {

    @Value("${app.firebase-bucket}")
    private String firebaseBucket;

    // img file 등록
    public String uploadFile(MultipartFile file, Long userId) throws IOException {
        Bucket bucket = StorageClient.getInstance().bucket(firebaseBucket);
        InputStream content = new ByteArrayInputStream(file.getBytes());
//        Blob blob = bucket.create(fileName.toString(), content, file.getContentType());
//        String storagePath = "posts/" + fileName.toString();
        String fileName = generatePostFileName(file.getOriginalFilename(), userId);
        Blob blob = bucket.create(fileName, content, file.getContentType());
        return blob.getMediaLink();
    }

    // img files 등록
    public List<String> uploadFiles(List<MultipartFile> files, Long userId) throws IOException {
        List<String> mediaLinks = new ArrayList<>();
        Bucket bucket = StorageClient.getInstance().bucket(firebaseBucket);

        for (MultipartFile file : files) {
            if (!file.isEmpty()) {
                String fileName = generatePostFileName(file.getOriginalFilename(), userId);
                InputStream content = new ByteArrayInputStream(file.getBytes());
                Blob blob = bucket.create(fileName, content, file.getContentType());
                mediaLinks.add(blob.getMediaLink());
            }
        }
        return mediaLinks;
    }

    // (게시글)파일 이름 생성
    public String generatePostFileName(String originalFilename, Long userId) {
        return "posts/" + userId + originalFilename;
    }

    // (프로필) 파일 이름 생성
    public String generateProfileFileName(String originalFilename, Long userId) {
        return "profile/" + userId + originalFilename;
    }

    // (게시글) 파일 불러오기
    public byte[] getFile(Long userId){
        Bucket bucket = StorageClient.getInstance().bucket(firebaseBucket);
        return bucket.get("posts/" + userId).getContent();
    }

    // (게시글) 파일들 불러오기
//    @Transactional
//    public void getFiles(Long userId){
//        Bucket bucket = StorageClient.getInstance().bucket(firebaseBucket);
//        bucket.
//        return bucket.get("posts/" + userId).listAll();
//    }
}
