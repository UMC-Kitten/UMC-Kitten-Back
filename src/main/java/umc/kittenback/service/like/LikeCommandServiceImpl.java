package umc.kittenback.service.like;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.kittenback.converter.LikeConverter;
import umc.kittenback.domain.Like;
import umc.kittenback.domain.Post;
import umc.kittenback.domain.User;
import umc.kittenback.repository.LikeRepository;
import umc.kittenback.repository.PostRepository;
import umc.kittenback.repository.UserRepository;

@Service
@RequiredArgsConstructor
@Transactional
public class LikeCommandServiceImpl implements LikeCommandService{

    private final LikeRepository likeRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    @Override
    public Like joinLike(Long userId, Long postId){
        User user = userRepository.findById(userId).get();
        Post post = postRepository.findById(postId).get();
        return likeRepository.save(LikeConverter.toLike(user, post));
    }

    @Override
    public Boolean deleteLike(Long likeId) {
        try{
            likeRepository.deleteById(likeId);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
}
