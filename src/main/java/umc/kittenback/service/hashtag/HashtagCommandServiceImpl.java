package umc.kittenback.service.hashtag;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.kittenback.converter.HashtagConverter;
import umc.kittenback.domain.Hashtag;
import umc.kittenback.domain.Post;
import umc.kittenback.repository.HashtagRepository;

@Service
@RequiredArgsConstructor
@Transactional
public class HashtagCommandServiceImpl implements HashtagCommandService{

    private final HashtagRepository hashtagRepository;
    @Override
    @Transactional
    public List<Hashtag> joinHashtag(Post post, List<String> hashtagList) {

        List<Hashtag> Hashtags = hashtagList.stream()
                .map(tag -> hashtagRepository.save(HashtagConverter.toHashtag(post, tag)))
                .collect(Collectors.toList());

        return Hashtags;
    }


}
