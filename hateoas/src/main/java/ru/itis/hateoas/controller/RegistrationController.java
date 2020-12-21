package ru.itis.hateoas.controller;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.itis.hateoas.dto.SignInDto;
import ru.itis.hateoas.dto.SignUpDto;
import ru.itis.hateoas.dto.TokenDto;
import ru.itis.hateoas.model.BlogUser;
import ru.itis.hateoas.service.SignInServiceImpl;

@Controller
public class RegistrationController {

    private final SignInServiceImpl authService;

    @Autowired
    public RegistrationController(SignInServiceImpl authService) {
        this.authService = authService;
    }

    @SneakyThrows
    @PostMapping("/signIn")
    public ResponseEntity<TokenDto> signIn(@RequestBody SignInDto signInDto) throws AccessDeniedException {
        return ResponseEntity.ok(authService.signIn(signInDto));
    }

    @PostMapping("/signUp")
    public ResponseEntity<BlogUser> signUp(@RequestBody SignUpDto signUpDto) {
        return ResponseEntity.ok(authService.signUp(signUpDto));
    }

}