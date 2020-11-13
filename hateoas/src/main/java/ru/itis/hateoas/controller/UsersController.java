package ru.itis.hateoas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.itis.hateoas.repository.BlogUsersRepository;
import ru.itis.hateoas.service.UsersService;

@RepositoryRestController
public class UsersController {

    @Autowired
    public UsersController(UsersService usersService) {
    }

}