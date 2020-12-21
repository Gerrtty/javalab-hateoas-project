package ru.itis.hateoas.controller;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.itis.hateoas.model.BlogUser;
import ru.itis.hateoas.service.SignInServiceImpl;

@RepositoryRestController
public class RegistrationController {

    private final SignInServiceImpl authService;

    @Autowired
    public RegistrationController(SignInServiceImpl authService) {
        this.authService = authService;
    }

    @RequestMapping(value = "blogUsers", method = RequestMethod.POST)
    public @ResponseBody ResponseEntity<?> register(@NotNull @RequestBody EntityModel<BlogUser> model) {
        return ResponseEntity.ok(authService.signUp(model));
    }

}