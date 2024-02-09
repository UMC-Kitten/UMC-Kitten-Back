package umc.kittenback.service.comment;


import umc.kittenback.domain.Comment;
import umc.kittenback.dto.comment.CommentRequestDTO.JoinCommentDTO;

public interface CommentCommandService {

    Comment joinComment(Long userId, Long postId, JoinCommentDTO req);

    Boolean deleteComment(Long commentId);

    Comment updatePost(Long commentId, JoinCommentDTO req);
}
