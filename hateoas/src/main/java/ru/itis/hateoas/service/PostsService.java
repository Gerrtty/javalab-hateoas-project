package ru.itis.hateoas.service;

import ru.itis.hateoas.model.BlogUser;
import ru.itis.hateoas.model.Post;

import java.util.List;

public interface PostsService {

    Post publish(Post post);
    void delete(Long id);
    List<Post> getAllByUser(BlogUser blogUser);
}
