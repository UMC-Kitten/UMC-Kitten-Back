package umc.kittenback.converter;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.data.domain.Page;
import umc.kittenback.domain.Comment;
import umc.kittenback.domain.Post;
import umc.kittenback.domain.User;
import umc.kittenback.web.dto.CommentRequestDTO;
import umc.kittenback.web.dto.CommentResponseDTO;
import umc.kittenback.web.dto.CommentResponseDTO.JoinCommentResultDTO;
import umc.kittenback.web.dto.PostResponseDTO;
import umc.kittenback.web.dto.PostResponseDTO.PostPreviewDTO;

public class CommentConverter {

    public static Comment toComment(User user, Post post, CommentRequestDTO.JoinCommentDTO req){
        return Comment.builder()
                .content(req.getContent())
                .post(post)
                .user(user)
                .build();
    }

    public static CommentResponseDTO.JoinCommentResultDTO toJoinCommentResultDTO(Comment comment) {
        return CommentResponseDTO.JoinCommentResultDTO.builder()
                .commentId(comment.getId())
                .createdAt(comment.getCreatedAt())
                .build();
    }

    public static CommentResponseDTO.CommentPreviewDTO toCommentPreviewDTO(Comment comment) {
        return CommentResponseDTO.CommentPreviewDTO.builder()
                .content(comment.getContent())
                .createdAt(comment.getCreatedAt())
                .build();
    }

    public static CommentResponseDTO.CommentPreviewListDTO toCommentPreviewListDTO(List<Comment> commentList){
        List<CommentResponseDTO.CommentPreviewDTO> commentPreviewDTOList =  commentList.stream()
                .map(CommentConverter::toCommentPreviewDTO).collect(Collectors.toList());

        return CommentResponseDTO.CommentPreviewListDTO.builder()
//                .isFirst(commentList.isFirst)
//                .isLast(commentList.isLast())
//                .totalPage(commentList.getTotalPages())
//                .totalElements(commentList.getTotalElements())
                .listSize(commentPreviewDTOList.size())
                .commentList(commentPreviewDTOList)
                .build();
    }
}
