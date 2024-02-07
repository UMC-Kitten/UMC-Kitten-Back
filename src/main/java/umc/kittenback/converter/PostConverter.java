package umc.kittenback.converter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.data.domain.Page;
import umc.kittenback.domain.FinancialLedger;
import umc.kittenback.domain.Hashtag;
import umc.kittenback.domain.Post;
import umc.kittenback.domain.User;
import umc.kittenback.domain.enums.PostType;

import umc.kittenback.web.dto.CommentResponseDTO;
import umc.kittenback.web.dto.CommentResponseDTO.CommentPreviewListDTO;
import umc.kittenback.web.dto.PostRequestDTO.JoinPostDTO;
import umc.kittenback.web.dto.PostResponseDTO;

public class PostConverter {

    public static PostResponseDTO.JoinPostResultDTO toJoinPostResultDTO(Post post){
        return PostResponseDTO.JoinPostResultDTO.builder()
                .postId(post.getId())
                .createdAt(post.getCreatedAt())
                .build();
    }

    public static Post toPost(User user, JoinPostDTO post){
        return Post.builder()
                .title(post.getTitle())
                .content(post.getContent())
                .postType(PostType.valueOf(post.getPostType()))
                .user(user)
                .build();
    }

    public static PostResponseDTO.PostPreviewDTO toPostPreviewDTO(Post post){
        CommentResponseDTO.CommentPreviewListDTO commentPreviewListDTO = CommentConverter.toCommentPreviewListDTO(post.getCommentList());

        return PostResponseDTO.PostPreviewDTO.builder()
                .title(post.getTitle())
                .content(post.getContent())
                .createdAt(post.getCreatedAt())
                .commentPreviewListDTO(commentPreviewListDTO)
//                .hashtagList(hashtagList)
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
