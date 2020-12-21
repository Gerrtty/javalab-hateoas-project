package ru.itis.hateoas.processor;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;
import ru.itis.hateoas.controller.CommentsController;
import ru.itis.hateoas.model.Comment;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class CommentRepresentationProcessor implements RepresentationModelProcessor<EntityModel<Comment>> {

    @Override
    public EntityModel<Comment> process(EntityModel<Comment> model) {

        Comment comment = model.getContent();

        if (comment != null) {
            model.add(linkTo(methodOn(CommentsController.class)
                    .publish(model)).withRel("/comments/"));
        }

        return model;
    }

}
