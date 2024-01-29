package umc.kittenback.service.post;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.kittenback.domain.Post;
import umc.kittenback.repository.PostRepository;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostQueryServiceImpl implements PostQueryService{

    private final PostRepository postRepository;
    @Override
    public List<Post> getPostList() {
        return postRepository.findAll();
    }
}
