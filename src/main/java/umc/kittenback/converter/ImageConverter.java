package umc.kittenback.converter;

import com.google.cloud.storage.Blob;
import java.util.List;
import java.util.stream.Collectors;
import umc.kittenback.domain.Post;
import umc.kittenback.dto.comment.CommentResponseDTO.CommentPreviewListDTO;
import umc.kittenback.dto.image.ImageResponseDTO;
import umc.kittenback.dto.image.ImageResponseDTO.ImagePreviewListDTO;
import umc.kittenback.dto.like.LikeResponseDTO.LikePreviewListDTO;
import umc.kittenback.dto.post.PostResponseDTO.PostPreviewDTO;

public class ImageConverter {


    public static ImageResponseDTO.ImageDTO toImagePreviewDTO(Blob blob){
        return ImageResponseDTO.ImageDTO.builder()
                .name(blob.getName())
                .contentType(blob.getContentType())
                .mediaLink(blob.getMediaLink())
                .build();
    }
    public static ImageResponseDTO.ImagePreviewListDTO toImagePreviewListDTO(List<Blob> blobs){
        List<ImageResponseDTO.ImageDTO> imageDTOList = blobs.stream()
                .map(ImageConverter::toImagePreviewDTO).collect(Collectors.toList());


        return ImageResponseDTO.ImagePreviewListDTO.builder()
                .imageList(imageDTOList)
                .build();
    }
}
