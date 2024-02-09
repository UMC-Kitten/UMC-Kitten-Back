package umc.kittenback.service.post;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.kittenback.converter.PostConverter;
import umc.kittenback.domain.Post;
import umc.kittenback.domain.User;
import umc.kittenback.repository.PostRepository;
import umc.kittenback.repository.UserRepository;
import umc.kittenback.dto.post.PostRequestDTO.JoinPostDTO;

@Service
@RequiredArgsConstructor
@Transactional
public class PostCommandServiceImpl implements PostCommandService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public Post joinPost(Long userId, JoinPostDTO req) {
        User user = userRepository.findById(userId).get();
        return postRepository.save(PostConverter.toPost(user, req));
    }

    @Override
    @Transactional
    public Boolean deletePost(Long postId) {
        postRepository.deleteById(postId);
        return true;
    }

    @Override
    @Transactional
    public Post updatePost(Long postId, JoinPostDTO req) {
        Post post = postRepository.findById(postId).get();
        post.setContent(req.getContent());
        post.setTitle(req.getTitle());
        return postRepository.save(post);
    }
}
