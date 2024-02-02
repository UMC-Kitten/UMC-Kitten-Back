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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FireBaseService {

    @Value("${app.firebase-bucket}")
    private String firebaseBucket;

    public String uploadFile(MultipartFile file, String fileName) throws IOException {
        Bucket bucket = StorageClient.getInstance().bucket(firebaseBucket);
        InputStream content = new ByteArrayInputStream(file.getBytes());
//        Blob blob = bucket.create(fileName.toString(), content, file.getContentType());
        String storagePath = "posts/" + fileName.toString();
        Blob blob = bucket.create(storagePath, content, file.getContentType());
        return blob.getMediaLink();
    }

    public List<String> uploadFiles(List<MultipartFile> files) throws IOException {
        List<String> mediaLinks = new ArrayList<>();
        Bucket bucket = StorageClient.getInstance().bucket(firebaseBucket);

        for (MultipartFile file : files) {
            if (!file.isEmpty()) {
                String fileName = generateFileName(file.getOriginalFilename());
                InputStream content = new ByteArrayInputStream(file.getBytes());
                Blob blob = bucket.create(fileName, content, file.getContentType());
                mediaLinks.add(blob.getMediaLink());
            }
        }
        return mediaLinks;
    }

    private String generateFileName(String originalFilename) {
        return "posts/" + originalFilename;
    }

}
