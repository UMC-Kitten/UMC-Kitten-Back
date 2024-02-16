package umc.kittenback.dto.image;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.google.cloud.storage.Blob;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
public class ImageResponseDTO {

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ImageDTO {
        String name;
        String contentType;
        String mediaLink;

    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ImagePreviewListDTO{
        List<ImageDTO> imageList;
//        Integer listSize;
//        Integer totalPage;
//        Long totalElements;
//        Boolean isFirst;
//        Boolean isLast;
    }
}
