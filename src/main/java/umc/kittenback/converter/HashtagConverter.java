package umc.kittenback.converter;

import umc.kittenback.domain.Hashtag;
import umc.kittenback.domain.Post;

public class HashtagConverter {
    public static Hashtag toHashtag(Post post, String tag) {
        return Hashtag.builder()
                .content(tag)
                .post(post)
                .build();
    }
}
