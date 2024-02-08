package umc.kittenback.service.hashtag;

import java.util.List;
import umc.kittenback.domain.Hashtag;
import umc.kittenback.domain.Post;

public interface HashtagQueryService {

    List<Hashtag> getHashList(Post post);
}
