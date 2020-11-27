package ru.itis.hateoas.processor;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;
import ru.itis.hateoas.controller.PostsController;
import ru.itis.hateoas.model.Post;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class PostsRepresentationProcessor implements RepresentationModelProcessor<EntityModel<Post>> {

    @Override
    public EntityModel<Post> process(EntityModel<Post> model) {
        Post post = model.getContent();

        if (post != null) {
            model.add(linkTo(methodOn(PostsController.class)
                    .publish(model)).withRel("/posts/"));
        }

        return model;
    }

}