package ru.itis.hateoas.controller;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.hateoas.dto.SignInDto;
import ru.itis.hateoas.dto.TokenDto;
import ru.itis.hateoas.service.SignInServiceImpl;

@RestController
public class AuthController {

    private final SignInServiceImpl authService;

    @Autowired
    public AuthController(SignInServiceImpl authService) {
        this.authService = authService;
    }

    @SneakyThrows
    @PostMapping("/signIn")
    public ResponseEntity<TokenDto> signIn(@RequestBody SignInDto signInDto) throws AccessDeniedException {
        return ResponseEntity.ok(authService.signIn(signInDto));
    }

}
