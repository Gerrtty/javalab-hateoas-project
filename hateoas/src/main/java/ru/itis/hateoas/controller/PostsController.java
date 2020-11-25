package ru.itis.hateoas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.itis.hateoas.service.PostsService;


@RepositoryRestController
public class PostsController {

    private final PostsService postsService;

    @Autowired
    public PostsController(PostsService postsService) {
        this.postsService = postsService;
    }

    @RequestMapping(value = "//", method = RequestMethod.PUT)
    public @ResponseBody
    ResponseEntity<?> publish() {
        return ResponseEntity.ok(EntityModel.of(postsService.getClass()));
    }

//    @RequestMapping(value = "//", method = RequestMethod.PUT)
//    public List<Post> publish() {
//        return null;
//    }

}
