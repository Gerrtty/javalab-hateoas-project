package ru.itis.hateoas.service;

import ru.itis.hateoas.model.Post;

public interface PostsService {
    Post publish(Post post);
}
