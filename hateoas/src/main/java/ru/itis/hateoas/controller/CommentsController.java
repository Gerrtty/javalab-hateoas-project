package ru.itis.hateoas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import ru.itis.hateoas.service.CommentsService;

@RepositoryRestController
public class CommentsController {

    @Autowired
    public CommentsController(CommentsService commentsService) {
    }

}