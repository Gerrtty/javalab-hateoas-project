package ru.itis.hateoas.controller;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.itis.hateoas.model.Post;
import ru.itis.hateoas.repository.PostsRepository;
import ru.itis.hateoas.service.PostsService;
import ru.itis.hateoas.service.PublishService;

@RepositoryRestController
public class PostsController {

    public final PublishService<Post> publishService;
    public final PostsService postsService;
    public final PostsRepository postsRepository;

    @Autowired
    public PostsController(PublishService<Post> publishService, PostsService postsService, PostsRepository postsRepository) {
        this.publishService = publishService;
        this.postsService = postsService;
        this.postsRepository = postsRepository;
    }

    @RequestMapping(value = "posts", method = RequestMethod.POST)
    public @ResponseBody ResponseEntity<?> publish(@NotNull @RequestBody EntityModel<Post> model) {
        return publishService.publish(model.getContent(), postsRepository);
    }

}
