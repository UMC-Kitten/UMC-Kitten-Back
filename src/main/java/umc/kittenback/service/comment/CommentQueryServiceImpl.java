package umc.kittenback.service.comment;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.kittenback.repository.CommentRepository;
import umc.kittenback.repository.PostRepository;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CommentQueryServiceImpl implements CommentQueryService{

    private final PostRepository postRepository;
    private final CommentRepository commentRepository;
}
