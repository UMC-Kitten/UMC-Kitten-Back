package umc.kittenback.converter;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.data.domain.Page;
import umc.kittenback.domain.Post;
import umc.kittenback.domain.User;
import umc.kittenback.domain.enums.PostType;

import umc.kittenback.dto.comment.CommentResponseDTO;
import umc.kittenback.dto.comment.CommentResponseDTO.CommentPreviewListDTO;
import umc.kittenback.dto.like.LikeResponseDTO.LikePreviewListDTO;
import umc.kittenback.dto.post.PostRequestDTO.JoinPostDTO;
import umc.kittenback.dto.post.PostResponseDTO;
import umc.kittenback.dto.post.PostResponseDTO.PostPreviewDTO;

public class PostConverter {

    public static PostResponseDTO.JoinPostResultDTO toJoinPostResultDTO(Post post){
        return PostResponseDTO.JoinPostResultDTO.builder()
                .postId(post.getId())
                .createdAt(post.getCreateDate())
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

    public static PostPreviewDTO toPostPreviewDTO(Post post){
        CommentPreviewListDTO commentPreviewListDTO = CommentConverter.toCommentPreviewListDTO(post.getCommentList());
        LikePreviewListDTO likePreviewListDTO = LikeConverter.toLikePreviewListDTO(post.getLikeList());

        return PostPreviewDTO.builder()
                .title(post.getTitle())
                .content(post.getContent())
                .writerNickName(post.getUser().getNickname())
                .createdAt(post.getCreateDate())
                .commentPreviewListDTO(commentPreviewListDTO)
                .likePreviewListDTO(likePreviewListDTO)
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
