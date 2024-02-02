package umc.kittenback.service.firebase;

import com.google.cloud.storage.Blob;
import com.google.cloud.storage.Bucket;
import com.google.firebase.cloud.StorageClient;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FireBaseService {

    @Value("${app.firebase-bucket}")
    private String firebaseBucket;

    public String uploadFiles(MultipartFile file, String fileName) throws IOException {
        Bucket bucket = StorageClient.getInstance().bucket(firebaseBucket);
        InputStream content = new ByteArrayInputStream(file.getBytes());
        Blob blob = bucket.create(fileName.toString(), content, file.getContentType());
        return blob.getMediaLink();
    }
}
