package ru.itis.hateoas.processor;

import org.jetbrains.annotations.NotNull;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;
import ru.itis.hateoas.controller.LikesController;
import ru.itis.hateoas.model.PostLike;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class LikesRepresentationProcessor implements RepresentationModelProcessor<EntityModel<PostLike>> {

    @Override
    public @NotNull EntityModel<PostLike> process(EntityModel<PostLike> model) {

        PostLike like = model.getContent();

        if (like != null) {
            model.add(linkTo(methodOn(LikesController.class)
                    .setLike(model)).withRel("/postLikes/"));
        }

        return model;
    }

}
