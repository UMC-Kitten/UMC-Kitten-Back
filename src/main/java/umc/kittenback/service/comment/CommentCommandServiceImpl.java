package umc.kittenback.service.comment;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.kittenback.converter.CommentConverter;
import umc.kittenback.domain.Comment;
import umc.kittenback.domain.Post;
import umc.kittenback.repository.CommentRepository;
import umc.kittenback.repository.PostRepository;
import umc.kittenback.web.dto.CommentRequestDTO.JoinCommentDTO;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CommentCommandServiceImpl implements CommentCommandService{

    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

    @Override
    public Comment joinComment(Long postId, JoinCommentDTO req) {
        Post post = postRepository.findById(postId).get();
        return commentRepository.save(CommentConverter.toComment(post, req));
    }

    @Override
    public Boolean deleteComment(Long commentId) {
        Comment comment = commentRepository.findById(commentId).get();
        commentRepository.delete(comment);
        return true;
    }

    @Override
    public Comment updatePost(Long commentId, JoinCommentDTO req) {
        Comment comment = commentRepository.findById(commentId).get();
        comment.setContent(req.getContent());
        return commentRepository.save(comment);
    }
}
