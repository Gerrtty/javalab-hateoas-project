//package ru.itis.hateoas.controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.hateoas.EntityModel;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.ResponseBody;
//import ru.itis.hateoas.model.Comment;
//import ru.itis.hateoas.repository.CommentsRepository;
//import ru.itis.hateoas.service.PublishService;
//
//public class CommentsController {
//
//    private final PublishService<Comment> publishService;
//    private final CommentsRepository commentsRepository;
//
//    @Autowired
//    public CommentsController(PublishService<Comment> publishService, CommentsRepository commentsRepository) {
//        this.publishService = publishService;
//        this.commentsRepository = commentsRepository;
//    }
//
//    @RequestMapping(value = "comments", method = RequestMethod.POST)
//    public @ResponseBody
//    ResponseEntity<?> publish(@RequestBody EntityModel<Comment> model) {
//        return publishService.publish(model, commentsRepository);
//    }
//
//}
