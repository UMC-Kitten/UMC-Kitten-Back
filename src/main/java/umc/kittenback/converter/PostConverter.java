package umc.kittenback.converter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.data.domain.Page;
import umc.kittenback.domain.Post;
import umc.kittenback.domain.enums.PostType;
import umc.kittenback.web.dto.PostRequestDTO.JoinPostDTO;
import umc.kittenback.web.dto.PostResponseDTO;

public class PostConverter {

    public static PostResponseDTO.JoinPostResultDTO toJoinPostResultDTO(Post post){
        return PostResponseDTO.JoinPostResultDTO.builder()
                .postId(post.getId())
                .createdAt(LocalDateTime.now())
                .build();
    }

    public static Post toPost(JoinPostDTO post){
        return Post.builder()
                .title(post.getTitle())
                .content(post.getContent())
                .postType(PostType.valueOf(post.getPostType()))
                .build();
    }

    public static PostResponseDTO.PostPreviewDTO toPostPreviewDTO(Post post){
        return PostResponseDTO.PostPreviewDTO.builder()
                .title(post.getTitle())
                .content(post.getContent())
                .createdAt(LocalDateTime.now())
                .build();
    }

    public static PostResponseDTO.PostPreviewListDTO toPostPreviewListDTO(Page<Post> postList){
        List<PostResponseDTO.PostPreviewDTO> postPreviewDTOList =  postList.stream()
                .map(PostConverter::toPostPreviewDTO).collect(Collectors.toList());

        return PostResponseDTO.PostPreviewListDTO.builder()
                .isFirst(postList.isFirst())
                .isLast(postList.isLast())
                .totalPage(postList.getTotalPages())
                .totalElements(postList.getTotalElements())
                .listSize(postPreviewDTOList.size())
                .postList(postPreviewDTOList)
                .build();
    }
}
