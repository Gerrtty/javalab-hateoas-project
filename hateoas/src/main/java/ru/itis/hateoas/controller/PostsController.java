package ru.itis.hateoas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.itis.hateoas.model.Post;
import ru.itis.hateoas.service.PostsService;

@RepositoryRestController
public class PostsController {

    private final PostsService postsService;

    @Autowired
    public PostsController(PostsService postsService) {
        this.postsService = postsService;
    }

    @RequestMapping(value = "posts", method = RequestMethod.POST)
    public @ResponseBody ResponseEntity<?> publish(@RequestBody EntityModel<Post> model) {

        Post post = model.getContent();

        if (post != null && post.getAuthor() != null && post.getAuthor().isConfirmed()) {
            return ResponseEntity.ok(EntityModel.of(postsService.publish(post)));
        }

        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(post);
    }

}
