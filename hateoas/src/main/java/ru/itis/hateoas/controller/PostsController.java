package ru.itis.hateoas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import ru.itis.hateoas.service.PostsService;

@RepositoryRestController
public class PostsController {

    @Autowired
    public PostsController(PostsService postsService) {
    }

}
