package umc.kittenback.service.comment;

import umc.kittenback.domain.Comment;
import umc.kittenback.web.dto.CommentRequestDTO.JoinCommentDTO;

public interface CommentCommandService {

    Comment joinComment(Long postId, JoinCommentDTO req);

    Boolean deleteComment(Long commentId);

    Comment updatePost(Long commentId, JoinCommentDTO req);
}
