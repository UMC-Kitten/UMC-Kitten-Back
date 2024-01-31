package umc.kittenback.service.post;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.kittenback.converter.PostConverter;
import umc.kittenback.domain.Post;
import umc.kittenback.repository.PostRepository;
import umc.kittenback.web.dto.PostRequestDTO.JoinPostDTO;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostCommandServiceImpl implements PostCommandService {

    private final PostRepository postRepository;
    @Override
    public Post joinPost(JoinPostDTO req) {
        return postRepository.save(PostConverter.toPost(req));
    }

    @Override
    public Boolean deletePost(Long postId) {
        postRepository.deleteById(postId);
        return true;
    }

    @Override
    public Post updatePost(Long postId, JoinPostDTO req) {
        Post post = postRepository.findById(postId).get();
        post.setContent(req.getContent());
        post.setTitle(req.getTitle());

        return postRepository.save(post);
    }
}
