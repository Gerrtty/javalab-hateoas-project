package ru.itis.hateoas.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.AccessException;
import org.springframework.stereotype.Service;
import ru.itis.hateoas.model.Post;
import ru.itis.hateoas.repository.PostsRepository;

@Service
public class PostsServiceImpl implements PostsService {

    private final PostsRepository postsRepository;
    private final PublishService<Post> postPublishService;

    @Autowired
    public PostsServiceImpl(PostsRepository postsRepository, PublishService<Post> postPublishService) {
        this.postsRepository = postsRepository;
        this.postPublishService = postPublishService;
    }

    @Override
    public Post publish(Post post) throws AccessException {
        return postPublishService.save(post, postsRepository);
    }

}
