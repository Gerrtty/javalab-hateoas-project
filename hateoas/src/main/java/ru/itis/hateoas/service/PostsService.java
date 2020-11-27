package ru.itis.hateoas.service;

import org.springframework.expression.AccessException;
import ru.itis.hateoas.model.Post;

public interface PostsService extends PublishInterface<Post> {
    Post publish(Post post) throws AccessException;
}
