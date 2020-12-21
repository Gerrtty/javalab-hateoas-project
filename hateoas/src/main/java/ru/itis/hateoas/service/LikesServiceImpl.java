package ru.itis.hateoas.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.hateoas.model.Post;
import ru.itis.hateoas.model.PostLike;
import ru.itis.hateoas.repository.LikesRepository;

import java.util.List;

@Service
public class LikesServiceImpl implements LikesService {

    private final LikesRepository likesRepository;

    @Autowired
    public LikesServiceImpl(LikesRepository likesRepository) {
        this.likesRepository = likesRepository;
    }

    @Override
    public PostLike set(PostLike postLike) {

        if (postLike != null) {
            Post post = postLike.getPost();

            Long likesCount = post.getLikesCount();

            if (likesCount == null) {
                likesCount = 0L;
            }

            List<PostLike> likes = post.getLikes();

            boolean likeIsExist = false;

            for (PostLike like : likes) {
                if (like.getAuthor().equals(postLike.getAuthor())) {
                    post.setLikesCount(likesCount - 1);
                    likesRepository.delete(like);
                    likeIsExist = true;
                    break;
                }
            }

            if(!likeIsExist) {
                post.setLikesCount(likesCount + 1);
                return likesRepository.save(postLike);
            }

//            else {
//                post.setLikesCount(likesCount - 1);
//                likesRepository.delete(postLike);
//            }

        }

        return null;

    }
}
