package umc.kittenback.service.hashtag;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.kittenback.domain.Hashtag;
import umc.kittenback.domain.Post;
import umc.kittenback.repository.HashtagRepository;

@Service
@RequiredArgsConstructor
@Transactional
public class HashtagQueryServiceImpl implements HashtagQueryService{

    private final HashtagRepository hashtagRepository;

    @Override
    @Transactional
    public List<Hashtag> getHashList(Post post) {
        List<Hashtag> hashtagList = hashtagRepository.findAllByPost(post);
        return hashtagList;
    }
}
