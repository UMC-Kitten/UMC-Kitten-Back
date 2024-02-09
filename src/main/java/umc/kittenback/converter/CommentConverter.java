package umc.kittenback.converter;

import java.util.List;
import java.util.stream.Collectors;
import umc.kittenback.domain.Comment;
import umc.kittenback.domain.Post;
import umc.kittenback.domain.User;
import umc.kittenback.dto.comment.CommentRequestDTO;
import umc.kittenback.dto.comment.CommentResponseDTO;

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
                .createdAt(comment.getCreateDate())
                .build();
    }

    public static CommentResponseDTO.CommentPreviewDTO toCommentPreviewDTO(Comment comment) {
        return CommentResponseDTO.CommentPreviewDTO.builder()
                .content(comment.getContent())
                .createdAt(comment.getCreateDate())
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
