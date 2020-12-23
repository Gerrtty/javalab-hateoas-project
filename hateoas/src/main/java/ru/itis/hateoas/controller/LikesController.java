package ru.itis.hateoas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.itis.hateoas.model.PostLike;
import ru.itis.hateoas.service.LikesService;

@RepositoryRestController
public class LikesController {

    private final LikesService likesService;

    @Autowired
    public LikesController(LikesService likesService) {
        this.likesService = likesService;
    }

    @RequestMapping(value = "postLikes", method = RequestMethod.POST)
    public @ResponseBody
    ResponseEntity<?> setLike(@RequestBody EntityModel<PostLike> model) {
        PostLike postLike = likesService.set(model.getContent());
        return ResponseEntity.ok(postLike);
    }

}
