package umc.kittenback.service.post;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.kittenback.domain.Post;
import umc.kittenback.domain.enums.PostType;
import umc.kittenback.repository.PostRepository;

@Service
@RequiredArgsConstructor
@Transactional
public class PostQueryServiceImpl implements PostQueryService{

    private final PostRepository postRepository;

    @Override
    @Transactional
    public Page<Post> getPostList(PostType postType, Integer page) {
        return postRepository.findAllByPostType(postType, PageRequest.of(page, 10));
    }

    @Override
    @Transactional
    public Post getPost(Long postId) {
        return postRepository.findById(postId).get();
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Post> getFreePostsOrderByLiKes(Integer page) {
        Pageable pageable = PageRequest.of(page, 10);
        return postRepository.findPostsByTypeOrderByLikes(PostType.FREE, pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Post> getBoastPostsOrderByCreateDate(Integer page) {
        Pageable pageable = PageRequest.of(page, 10);
        return postRepository.findByPostTypeOrderByCreateDate(PostType.BOAST, pageable);
    }
}
