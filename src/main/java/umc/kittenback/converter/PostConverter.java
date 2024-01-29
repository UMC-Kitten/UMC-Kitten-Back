package umc.kittenback.converter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import umc.kittenback.domain.Post;
import umc.kittenback.web.dto.PostRequestDTO;
import umc.kittenback.web.dto.PostResponseDTO;

public class PostConverter {

    public static PostResponseDTO.JoinPostResultDTO toJoinPostResultDTO(Post post){
        return PostResponseDTO.JoinPostResultDTO.builder()
                .postId(post.getId())
                .createdAt(LocalDateTime.now())
                .build();
    }

    public static Post toPost(PostRequestDTO.JoinPostDTO post){
        return Post.builder()
                .title(post.getTitle())
                .content(post.getContent())
                .build();
    }

    public static PostResponseDTO.PostPreviewDTO topostPreviewDTO(Post post){
        return PostResponseDTO.PostPreviewDTO.builder()
                .title(post.getTitle())
                .content(post.getContent())
                .createdAt(LocalDateTime.now())
                .build();
    }

    public static PostResponseDTO.PostPreviewListDTO toPostPreviewListDTO(List<Post> postList){
        List<PostResponseDTO.PostPreviewDTO> postPreviewDTOList =  postList.stream()
                .map(PostConverter::topostPreviewDTO).collect(Collectors.toList());

        return PostResponseDTO.PostPreviewListDTO.builder()
                .listSize(postPreviewDTOList.size())
                .postList(postPreviewDTOList)
                .build();
    }
}
