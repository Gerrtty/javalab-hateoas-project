package ru.itis.hateoas.processor;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;
import ru.itis.hateoas.controller.RegistrationController;
import ru.itis.hateoas.model.BlogUser;
import ru.itis.hateoas.service.SignInServiceImpl;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class UsersRepresentationProcessor implements RepresentationModelProcessor<EntityModel<BlogUser>> {

    private final SignInServiceImpl authService;

    @Autowired
    public UsersRepresentationProcessor(SignInServiceImpl authService) {
        this.authService = authService;
    }

    @Override
    public @NotNull EntityModel<BlogUser> process(EntityModel<BlogUser> model) {

        BlogUser user = model.getContent();

        if (user != null) {

            model.add(linkTo(methodOn(RegistrationController.class)
                    .register(model)).withRel("/blogUsers/"));

            authService.signUp(model);
        }

        return model;

    }

}
